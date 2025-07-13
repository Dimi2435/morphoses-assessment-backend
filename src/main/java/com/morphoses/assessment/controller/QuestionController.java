package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.AnswerSubmissionRequest;
import com.morphoses.assessment.service.QuestionService;
import com.morphoses.assessment.service.ReportService;
import com.morphoses.assessment.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_BASE_PATH + "/v" + AppConstants.DEFAULT_API_VERSION)
public class QuestionController {

  @Autowired private QuestionService questionService;
  @Autowired private ReportService reportService;

  @Operation(
      summary = "Get relevant questions",
      description = "Retrieves relevant questions for a user based on session ID.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Questions retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Session or user not found")
      })
  @GetMapping("/sessions/{sessionId}/users/{userId}/questions")
  public ResponseEntity<List<String>> getRelevantQuestions(
      @PathVariable UUID sessionId, @PathVariable UUID userId) {
    List<String> questions = questionService.getRelevantQuestionsForUser(userId, sessionId);
    return ResponseEntity.ok(questions);
  }

  @Operation(summary = "Submit answers", description = "Submits answers for a user in a session.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Answers submitted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping("/sessions/{sessionId}/users/{userId}/answers")
  public ResponseEntity<String> submitAnswers(
      @PathVariable UUID sessionId,
      @PathVariable UUID userId,
      @RequestBody AnswerSubmissionRequest request) {
    questionService.submitAnswers(userId, sessionId, request.getAnswers());
    return ResponseEntity.status(HttpStatus.CREATED).body("Answers submitted successfully.");
  }

  @Operation(
      summary = "Get review results",
      description = "Retrieves review results for a kid in a session.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Review results retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Session or kid not found")
      })
  @GetMapping("/sessions/{sessionId}/reviews/kids/{kidId}")
  public ResponseEntity<Map<String, Object>> getReviewResults(
      @PathVariable UUID sessionId, @PathVariable UUID kidId) {
    Map<String, Object> reviewResults = reportService.getReviewResultsForKid(sessionId, kidId);
    return ResponseEntity.ok(reviewResults);
  }
}
