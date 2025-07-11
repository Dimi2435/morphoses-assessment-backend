package com.morphoses.assessment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.dto.AnswerSubmissionRequest;
import com.morphoses.assessment.service.QuestionService;
import com.morphoses.assessment.service.ReportService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class QuestionControllerTest {

  @Mock private QuestionService questionService;

  @Mock private ReportService reportService;

  @InjectMocks private QuestionController questionController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetRelevantQuestions() {
    UUID sessionId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    List<String> questions = List.of("Question 1", "Question 2");

    when(questionService.getRelevantQuestionsForUser(userId, sessionId)).thenReturn(questions);

    ResponseEntity<List<String>> response =
        questionController.getRelevantQuestions(sessionId, userId);

    assertEquals(questions, response.getBody());
  }

  @Test
  void testSubmitAnswers() {
    UUID sessionId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    AnswerSubmissionRequest request = new AnswerSubmissionRequest();

    ResponseEntity<String> response = questionController.submitAnswers(sessionId, userId, request);

    assertEquals("Answers submitted successfully.", response.getBody());
  }

  @Test
  void testGetReviewResults() {
    UUID sessionId = UUID.randomUUID();
    UUID kidId = UUID.randomUUID();
    Map<String, Object> reviewResults = Map.of("result", "pass");

    when(reportService.getReviewResultsForKid(sessionId, kidId)).thenReturn(reviewResults);

    ResponseEntity<Map<String, Object>> response =
        questionController.getReviewResults(sessionId, kidId);

    assertEquals(reviewResults, response.getBody());
  }
}
