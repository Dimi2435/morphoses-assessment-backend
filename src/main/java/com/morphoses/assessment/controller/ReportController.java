package com.morphoses.assessment.controller;

import com.morphoses.assessment.service.ReportService;
import com.morphoses.assessment.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing reports. Provides endpoints for retrieving session summaries.
 *
 * <p>Author: Dimitrios Milios
 */
@RestController
@RequestMapping(AppConstants.REPORTS_ENDPOINT)
public class ReportController {

  @Autowired private ReportService reportService;

  @Operation(
      summary = "Get session summary",
      description = "Retrieves the summary of a session by its ID.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Session summary retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Session not found")
      })
  @GetMapping("/session-summary/{sessionId}")
  public ResponseEntity<Map<String, Object>> getSessionSummary(@PathVariable UUID sessionId) {
    Map<String, Object> summary = reportService.getSessionSummary(sessionId);
    return ResponseEntity.ok(summary);
  }
}
