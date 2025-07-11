package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.AnswerSubmissionRequest;
import com.morphoses.assessment.service.QuestionService;
import com.morphoses.assessment.service.ReportService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuestionController {

  @Autowired private QuestionService questionService;
  @Autowired private ReportService reportService;

  @GetMapping("/sessions/{sessionId}/users/{userId}/questions")
  public ResponseEntity<List<String>> getRelevantQuestions(
      @PathVariable UUID sessionId, @PathVariable UUID userId) {
    List<String> questions = questionService.getRelevantQuestionsForUser(userId, sessionId);
    return ResponseEntity.ok(questions);
  }

  @PostMapping("/sessions/{sessionId}/users/{userId}/answers")
  public ResponseEntity<String> submitAnswers(
      @PathVariable UUID sessionId,
      @PathVariable UUID userId,
      @RequestBody AnswerSubmissionRequest request) {
    questionService.submitAnswers(userId, sessionId, request.getAnswers());
    return ResponseEntity.status(HttpStatus.CREATED).body("Answers submitted successfully.");
  }

  @GetMapping("/sessions/{sessionId}/reviews/kids/{kidId}")
  public ResponseEntity<Map<String, Object>> getReviewResults(
      @PathVariable UUID sessionId, @PathVariable UUID kidId) {
    Map<String, Object> reviewResults = reportService.getReviewResultsForKid(sessionId, kidId);
    return ResponseEntity.ok(reviewResults);
  }
}
