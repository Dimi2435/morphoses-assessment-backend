package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.SessionCreateRequest;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.service.SessionService;
import com.morphoses.assessment.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing sessions.
 * Provides endpoints for creating, completing, and retrieving sessions.
 * 
 * Author: Dimitrios Milios
 */
@RestController
@RequestMapping(AppConstants.API_BASE_PATH + AppConstants.SESSIONS_ENDPOINT)
public class SessionController {

  @Autowired private SessionService sessionService;

  @Operation(summary = "Create a new session", description = "Creates a new session in the system")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Session created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping
  public ResponseEntity<Session> createSession(@RequestBody SessionCreateRequest request) {
    Session session =
        sessionService.createSession(
            request.getClassroomId(),
            request.getStartTime(),
            request.getEndTime(),
            request.getSoftSkillIds(),
            request.getParticipantIds());
    return new ResponseEntity<>(session, HttpStatus.CREATED);
  }

  @Operation(summary = "Complete a session", description = "Marks a session as completed.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Session completed successfully"),
        @ApiResponse(responseCode = "404", description = "Session or instructor not found")
      })
  @PutMapping("/{sessionId}/complete/{instructorId}")
  public ResponseEntity<Session> completeSession(
      @PathVariable UUID sessionId, @PathVariable UUID instructorId) {
    Session session = sessionService.completeSession(sessionId, instructorId);
    return ResponseEntity.ok(session);
  }

  @Operation(summary = "Mark kid absent", description = "Marks a kid as absent in a session.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Kid marked absent successfully"),
        @ApiResponse(responseCode = "404", description = "Session or kid not found")
      })
  @PutMapping("/{sessionId}/participants/{kidId}/absent")
  public ResponseEntity<Session> markKidAbsent(
      @PathVariable UUID sessionId, @PathVariable UUID kidId) {
    Session session = sessionService.markKidAbsent(sessionId, kidId);
    return ResponseEntity.ok(session);
  }

  @Operation(summary = "Get session by ID", description = "Retrieves a session by its unique ID.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Session found"),
        @ApiResponse(responseCode = "404", description = "Session not found")
      })
  @GetMapping("/{id}")
  public ResponseEntity<Session> getSessionById(@PathVariable UUID id) {
    Session session = sessionService.getSessionById(id);
    return ResponseEntity.ok(session);
  }
}
