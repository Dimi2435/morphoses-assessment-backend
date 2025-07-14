package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.Classroom;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.entity.SessionParticipant;
import com.morphoses.assessment.entity.SoftSkill;
import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.exception.ClassroomNotFoundException;
import com.morphoses.assessment.exception.InvalidOperationException;
import com.morphoses.assessment.exception.SessionNotFoundException;
import com.morphoses.assessment.exception.SoftSkillNotFoundException;
import com.morphoses.assessment.exception.UserNotFoundException;
import com.morphoses.assessment.repository.ClassroomRepository;
import com.morphoses.assessment.repository.SessionParticipantRepository;
import com.morphoses.assessment.repository.SessionRepository;
import com.morphoses.assessment.repository.SoftSkillRepository;
import com.morphoses.assessment.repository.UserRepository;
import com.morphoses.assessment.util.UserType;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing sessions in the Morphoses Assessment application.
 *
 * This class provides methods to create, complete, and manage sessions.
 *
 * Author: Dimitrios Milios
 */
@Service
public class SessionService {

    @Autowired private SessionRepository sessionRepository;
    @Autowired private ClassroomRepository classroomRepository;
    @Autowired private SoftSkillRepository softSkillRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private SessionParticipantRepository sessionParticipantRepository;

    /**
     * Creates a new session with the specified details.
     *
     * @param classroomId the ID of the classroom for the session
     * @param startTime the start time of the session
     * @param endTime the end time of the session
     * @param softSkillIds the IDs of the soft skills targeted in the session
     * @param participantIds the IDs of the participants in the session
     * @return the created Session object
     * @throws ClassroomNotFoundException if the classroom ID is invalid
     * @throws SoftSkillNotFoundException if any soft skill ID is invalid
     * @throws UserNotFoundException if any participant ID is invalid
     * @throws InvalidOperationException if the session does not meet the required conditions
     */
    @Transactional
    public Session createSession(
        UUID classroomId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Set<UUID> softSkillIds,
        Set<UUID> participantIds) {

    Classroom classroom =
        classroomRepository
            .findById(classroomId)
            .orElseThrow(
                () ->
                    new ClassroomNotFoundException(
                        "Classroom with ID " + classroomId + " not found."));

    if (softSkillIds == null || softSkillIds.isEmpty() || softSkillIds.size() > 3) {
      throw new IllegalArgumentException("A session must target 1 to 3 soft skills.");
    }
    Set<SoftSkill> softSkills =
        softSkillIds.stream()
            .map(
                id ->
                    softSkillRepository
                        .findById(id)
                        .orElseThrow(
                            () ->
                                new SoftSkillNotFoundException(
                                    "SoftSkill with ID " + id + " not found.")))
            .collect(Collectors.toSet());

    if (participantIds == null || participantIds.isEmpty()) {
      throw new IllegalArgumentException("A session must have participants.");
    }
    Set<User> participants =
        participantIds.stream()
            .map(
                id ->
                    userRepository
                        .findById(id)
                        .orElseThrow(
                            () -> new UserNotFoundException("User with ID " + id + " not found.")))
            .collect(Collectors.toSet());

    // Validate at least one instructor and one kid (typical classroom setup, though not explicitly
    // in requirements)
    boolean hasInstructor =
        participants.stream().anyMatch(p -> p.getUserType() == UserType.INSTRUCTOR);
    boolean hasKid = participants.stream().anyMatch(p -> p.getUserType() == UserType.KID);

    if (!hasInstructor) {
      throw new InvalidOperationException("A session must have at least one instructor.");
    }
    if (!hasKid) {
      throw new InvalidOperationException("A session must have at least one kid.");
    }

    Session session = new Session();
    session.setClassroom(classroom);
    session.setStartTime(startTime);
    session.setEndTime(endTime);
    session.setCompleted(false);
    session.setSoftSkills(softSkills);

    session = sessionRepository.save(session); // Save session first to get its ID

    for (User user : participants) {
      session.addParticipant(user, false); // Default to not absent initially
    }
    return sessionRepository.save(session); // Save again to persist participants
  }

    /**
     * Completes a session with the specified session ID and instructor ID.
     *
     * @param sessionId the ID of the session to complete
     * @param instructorId the ID of the instructor completing the session
     * @return the completed Session object
     * @throws SessionNotFoundException if the session ID is invalid
     * @throws UserNotFoundException if the instructor ID is invalid
     * @throws InvalidOperationException if the instructor is not authorized to complete the session
     */
    @Transactional
    public Session completeSession(UUID sessionId, UUID instructorId) {
    Session session =
        sessionRepository
            .findById(sessionId)
            .orElseThrow(
                () -> new SessionNotFoundException("Session with ID " + sessionId + " not found."));
    User instructor =
        userRepository
            .findById(instructorId)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        "Instructor with ID " + instructorId + " not found."));

    if (instructor.getUserType() != UserType.INSTRUCTOR) {
      throw new InvalidOperationException("Only instructors can complete a session.");
    }

    // Check if the instructor is actually a participant in this session
    sessionParticipantRepository
        .findBySessionAndUser(session, instructor)
        .orElseThrow(
            () ->
                new InvalidOperationException("Instructor is not a participant in this session."));

    if (session.isCompleted()) {
      throw new InvalidOperationException("Session is already marked as completed.");
    }

    session.setCompleted(true);
    return sessionRepository.save(session);
  }

    /**
     * Marks a kid as absent for a specified session.
     *
     * @param sessionId the ID of the session
     * @param kidId the ID of the kid to mark as absent
     * @return the updated Session object
     * @throws SessionNotFoundException if the session ID is invalid
     * @throws UserNotFoundException if the kid ID is invalid
     * @throws InvalidOperationException if the kid is not a participant in the session
     */
    @Transactional
    public Session markKidAbsent(UUID sessionId, UUID kidId) {
    Session session =
        sessionRepository
            .findById(sessionId)
            .orElseThrow(
                () -> new SessionNotFoundException("Session with ID " + sessionId + " not found."));
    User kid =
        userRepository
            .findById(kidId)
            .orElseThrow(() -> new UserNotFoundException("Kid with ID " + kidId + " not found."));

    if (kid.getUserType() != UserType.KID) {
      throw new InvalidOperationException("User with ID " + kidId + " is not a kid.");
    }

    SessionParticipant participant =
        sessionParticipantRepository
            .findBySessionAndUser(session, kid)
            .orElseThrow(
                () ->
                    new InvalidOperationException(
                        "Kid with ID " + kidId + " is not a participant in this session."));

    if (participant.isAbsent()) {
      throw new InvalidOperationException("Kid is already marked as absent for this session.");
    }

    participant.setAbsent(true);
    sessionParticipantRepository.save(participant); // Save the updated participant record
    return session; // Return the session, which now reflects the updated participant status
  }

    /**
     * Retrieves a session by its unique ID.
     *
     * @param id the unique ID of the session
     * @return the Session object associated with the given ID
     * @throws SessionNotFoundException if no session is found with the given ID
     */
    public Session getSessionById(UUID id) {
    return sessionRepository
        .findById(id)
        .orElseThrow(() -> new SessionNotFoundException("Session not found with ID: " + id));
  }
}
