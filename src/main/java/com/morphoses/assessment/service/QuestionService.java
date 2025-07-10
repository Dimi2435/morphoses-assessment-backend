package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.InstructorAnswer;
import com.morphoses.assessment.entity.KidAnswer;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.entity.SessionParticipant;
import com.morphoses.assessment.entity.SoftSkill;
import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.exception.EntityNotFoundException;
import com.morphoses.assessment.exception.InvalidOperationException;
import com.morphoses.assessment.repository.InstructorAnswerRepository;
import com.morphoses.assessment.repository.KidAnswerRepository;
import com.morphoses.assessment.repository.SessionParticipantRepository;
import com.morphoses.assessment.repository.SessionRepository;
import com.morphoses.assessment.repository.SoftSkillRepository;
import com.morphoses.assessment.repository.UserRepository;
import com.morphoses.assessment.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SoftSkillRepository softSkillRepository;
    @Autowired
    private SessionParticipantRepository sessionParticipantRepository;
    @Autowired
    private InstructorAnswerRepository instructorAnswerRepository;
    @Autowired
    private KidAnswerRepository kidAnswerRepository;

    public List<String> getRelevantQuestionsForUser(UUID userId, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session with ID " + sessionId + " not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found."));

        // Check if user is a participant of the session
        SessionParticipant participant = sessionParticipantRepository.findBySessionAndUser(session, user)
                .orElseThrow(() -> new InvalidOperationException("User is not a participant in this session."));

       // Questions can only be retrieved for sessions that are NOT completed [cite: 16]
        if (session.isCompleted()) {
            throw new InvalidOperationException("Questions cannot be retrieved for a completed session.");
        }

        List<String> questions = new ArrayList<>();
        Set<SoftSkill> sessionSoftSkills = session.getSoftSkills();

        if (user.getUserType() == UserType.INSTRUCTOR) {
            // Get all kids present in the session
            List<User> kidsInSession = sessionParticipantRepository.findBySessionAndUserUserTypeAndIsAbsentFalse(session, UserType.KID)
                    .stream()
                    .map(SessionParticipant::getUser)
                    .collect(Collectors.toList());

            for (User kid : kidsInSession) {
                for (SoftSkill softSkill : sessionSoftSkills) {
                    questions.add(String.format("Has %s understood the meaning of %s?", kid.getName(), softSkill.getName()));
                }
            }
        }
        else if (user.getUserType() == UserType.KID) {
            if (participant.isAbsent()) {
                throw new InvalidOperationException("Absent kids are not eligible for review.");
            }
            for (SoftSkill softSkill : sessionSoftSkills) {
                questions.add(String.format("Have you understood the meaning of %s?", softSkill.getName()));
            }
        } else {
            throw new InvalidOperationException("Unsupported user type for question retrieval.");
        }
        return questions;
    }

    @Transactional
    public void submitAnswers(UUID userId, UUID sessionId, Map<UUID, Integer> answers) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session with ID " + sessionId + " not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found."));

        // Check if user is a participant of the session
        SessionParticipant participant = sessionParticipantRepository.findBySessionAndUser(session, user)
                .orElseThrow(() -> new InvalidOperationException("User is not a participant in this session."));

        // Answers can only be submitted if the session is completed [cite: 16]
        if (!session.isCompleted()) {
            throw new InvalidOperationException("Answers can only be submitted for a completed session.");
        }

        // Validate answers (1-5) [cite: 15] and soft skills
        Set<UUID> sessionSoftSkillIds = session.getSoftSkills().stream()
                .map(SoftSkill::getId)
                .collect(Collectors.toSet());

        for (Map.Entry<UUID, Integer> entry : answers.entrySet()) {
            UUID softSkillId = entry.getKey();
            Integer answerValue = entry.getValue();

            if (answerValue == null || answerValue < 1 || answerValue > 5) {
                throw new IllegalArgumentException("Answer for soft skill " + softSkillId + " must be between 1 and 5.");
            }
            if (!sessionSoftSkillIds.contains(softSkillId)) {
                throw new InvalidOperationException("Soft skill " + softSkillId + " is not targeted in this session.");
            }
        }

        if (user.getUserType() == UserType.INSTRUCTOR) {
            List<User> kidsInSession = sessionParticipantRepository.findBySessionAndUserUserTypeAndIsAbsentFalse(session, UserType.KID)
                    .stream()
                    .map(SessionParticipant::getUser)
                    .collect(Collectors.toList());

            for (User kid : kidsInSession) {
                for (SoftSkill softSkill : session.getSoftSkills()) {
                    if (answers.containsKey(softSkill.getId())) {
                        InstructorAnswer instructorAnswer = new InstructorAnswer(session, user, kid, softSkill, answers.get(softSkill.getId()));
                        instructorAnswerRepository.save(instructorAnswer);
                    } else {
                        // Handle case where instructor didn't provide an answer for a soft skill for a kid
                        // (could be an error or business rule allows partial answers)
                        throw new InvalidOperationException(String.format("Instructor must provide an answer for kid %s and soft skill %s.", kid.getName(), softSkill.getName()));
                    }
                }
            }
        } else if (user.getUserType() == UserType.KID) {
            if (participant.isAbsent()) {
                throw new InvalidOperationException("Absent kids cannot submit answers.");
            }
            for (SoftSkill softSkill : session.getSoftSkills()) {
                if (answers.containsKey(softSkill.getId())) {
                    KidAnswer kidAnswer = new KidAnswer(session, user, softSkill, answers.get(softSkill.getId()));
                    kidAnswerRepository.save(kidAnswer);
                } else {
                    // Handle case where kid didn't provide an answer for a soft skill
                    throw new InvalidOperationException(String.format("Kid must provide an answer for soft skill %s.", softSkill.getName()));
                }
            }
        }
    }
}