package com.morphoses.assessment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.service.ReportService;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class ReportControllerTest {

  @Mock private ReportService reportService;

  @InjectMocks private ReportController reportController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetSessionSummary() {
    UUID sessionId = UUID.randomUUID();
    Map<String, Object> summary = Map.of("summary", "details");

    when(reportService.getSessionSummary(sessionId)).thenReturn(summary);

    ResponseEntity<Map<String, Object>> response = reportController.getSessionSummary(sessionId);

    assertEquals(summary, response.getBody());
  }
}
