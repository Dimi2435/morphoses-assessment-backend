package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.SessionCreateRequest;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.service.SessionService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

  @Autowired private SessionService sessionService;

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

  @PutMapping("/{sessionId}/complete/{instructorId}")
  public ResponseEntity<Session> completeSession(
      @PathVariable UUID sessionId, @PathVariable UUID instructorId) {
    Session session = sessionService.completeSession(sessionId, instructorId);
    return ResponseEntity.ok(session);
  }

  @PutMapping("/{sessionId}/participants/{kidId}/absent")
  public ResponseEntity<Session> markKidAbsent(
      @PathVariable UUID sessionId, @PathVariable UUID kidId) {
    Session session = sessionService.markKidAbsent(sessionId, kidId);
    return ResponseEntity.ok(session);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Session> getSessionById(@PathVariable UUID id) {
    Session session = sessionService.getSessionById(id);
    return ResponseEntity.ok(session);
  }
}
