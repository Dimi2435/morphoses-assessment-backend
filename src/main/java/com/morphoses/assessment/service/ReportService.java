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
import com.morphoses.assessment.repository.UserRepository;
import com.morphoses.assessment.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InstructorAnswerRepository instructorAnswerRepository;
    @Autowired
    private KidAnswerRepository kidAnswerRepository;
    @Autowired
    private SessionParticipantRepository sessionParticipantRepository;

    /**
     * [cite_start]Retrieves and compares the answers of an instructor for a kid and the specified kid's own answers for a session. [cite: 18]
     *
     * @param sessionId The ID of the session.
     * @param kidId     The ID of the kid.
     * @return A map containing instructor answers, kid answers, and a comparison.
     */
    public Map<String, Object> getReviewResultsForKid(UUID sessionId, UUID kidId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session with ID " + sessionId + " not found."));
        User kid = userRepository.findById(kidId)
                .orElseThrow(() -> new EntityNotFoundException("Kid with ID " + kidId + " not found."));

        if (kid.getUserType() != UserType.KID) {
            throw new InvalidOperationException("User with ID " + kidId + " is not a kid.");
        }

        // Ensure session is completed for review results [cite: 16]
        if (!session.isCompleted()) {
            throw new InvalidOperationException("Review results are only available for completed sessions.");
        }

        // Ensure kid participated in the session and was not absent
        SessionParticipant participant = sessionParticipantRepository.findBySessionAndUser(session, kid)
                .orElseThrow(() -> new InvalidOperationException("Kid with ID " + kidId + " did not participate in this session."));
            if (participant.isAbsent()) {
            throw new InvalidOperationException("Absent kids are not eligible for review results.");
        }

        List<InstructorAnswer> instructorAnswers = instructorAnswerRepository.findBySessionAndKid(session, kid);
        List<KidAnswer> kidAnswers = kidAnswerRepository.findBySessionAndKid(session, kid);

        Map<String, Object> results = new HashMap<>();
        results.put("kidId", kidId);
        results.put("kidName", kid.getName());
        results.put("sessionId", sessionId);
        results.put("sessionName", session.getClassroom().getName() + " - " + session.getStartTime());

        Map<String, Integer> instructorReview = instructorAnswers.stream()
                .collect(Collectors.toMap(
                        ans -> ans.getSoftSkill().getName(),
                        InstructorAnswer::getAnswer
                ));
        results.put("instructorReview", instructorReview);

        Map<String, Integer> kidSelfReview = kidAnswers.stream()
                .collect(Collectors.toMap(
                        ans -> ans.getSoftSkill().getName(),
                        KidAnswer::getAnswer
                ));
        results.put("kidSelfReview", kidSelfReview);

        Map<String, String> comparison = new HashMap<>();
        for (SoftSkill softSkill : session.getSoftSkills()) {
            Integer instAnswer = instructorReview.get(softSkill.getName());
            Integer kidAnswer = kidSelfReview.get(softSkill.getName());

            if (instAnswer != null && kidAnswer != null) {
                if (instAnswer.equals(kidAnswer)) {
                    comparison.put(softSkill.getName(), "Match");
                } else if (instAnswer > kidAnswer) {
                    comparison.put(softSkill.getName(), "Instructor rated higher (Difference: " + (instAnswer - kidAnswer) + ")");
                } else {
                    comparison.put(softSkill.getName(), "Kid rated higher (Difference: " + (kidAnswer - instAnswer) + ")");
                }
            } else {
                comparison.put(softSkill.getName(), "Incomplete data");
            }
        }
        results.put("comparison", comparison);

        return results;
    }

    /**
     * [cite_start]Provides a summary report for a given session. [cite: 19]
     *
     * @param sessionId The ID of the session.
     * @return A map containing various summary metrics for the session.
     */
    public Map<String, Object> getSessionSummary(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session with ID " + sessionId + " not found."));

        Map<String, Object> summary = new HashMap<>();
        summary.put("sessionId", session.getId());
        summary.put("classroomName", session.getClassroom().getName());
        summary.put("sessionTime", session.getStartTime() + " to " + session.getEndTime());
        summary.put("isCompleted", session.isCompleted());

        Set<SoftSkill> softSkills = session.getSoftSkills();
        summary.put("targetedSoftSkills", softSkills.stream().map(SoftSkill::getName).collect(Collectors.toList()));

        List<SessionParticipant> allParticipants = sessionParticipantRepository.findBySession(session);
        long totalParticipants = allParticipants.size();
        long totalKids = allParticipants.stream().filter(p -> p.getUser().getUserType() == UserType.KID).count();
        long totalInstructors = allParticipants.stream().filter(p -> p.getUser().getUserType() == UserType.INSTRUCTOR).count();
        long absentKids = allParticipants.stream().filter(p -> p.getUser().getUserType() == UserType.KID && p.isAbsent()).count();
        long presentKids = totalKids - absentKids;

        summary.put("totalParticipants", totalParticipants);
        summary.put("totalKids", totalKids);
        summary.put("totalInstructors", totalInstructors);
        summary.put("absentKids", absentKids);
        summary.put("presentKids", presentKids);

        if (session.isCompleted()) {
            // Aggregate average ratings if session is completed
            Map<String, Double> averageInstructorRatings = new HashMap<>();
            Map<String, Double> averageKidSelfRatings = new HashMap<>();

            for (SoftSkill softSkill : softSkills) {
                double avgInst = instructorAnswerRepository.findBySessionAndKid(session, null) // Needs a custom query for all kids for a soft skill
                        .stream()
                        .filter(ia -> ia.getSoftSkill().equals(softSkill))
                        .mapToInt(InstructorAnswer::getAnswer)
                        .average()
                        .orElse(0.0);
                averageInstructorRatings.put(softSkill.getName(), avgInst);

                double avgKid = kidAnswerRepository.findBySessionAndKid(session, null) // Needs a custom query for all kids for a soft skill
                        .stream()
                        .filter(ka -> ka.getSoftSkill().equals(softSkill))
                        .mapToInt(KidAnswer::getAnswer)
                        .average()
                        .orElse(0.0);
                averageKidSelfRatings.put(softSkill.getName(), avgKid);
            }
            summary.put("averageInstructorRatingsPerSoftSkill", averageInstructorRatings);
            summary.put("averageKidSelfRatingsPerSoftSkill", averageKidSelfRatings);
        } else {
            summary.put("averageInstructorRatingsPerSoftSkill", "N/A (Session not completed)");
            summary.put("averageKidSelfRatingsPerSoftSkill", "N/A (Session not completed)");
        }

        return summary;
    }
}