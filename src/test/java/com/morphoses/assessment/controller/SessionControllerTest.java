package com.morphoses.assessment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.dto.SessionCreateRequest;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.service.SessionService;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class SessionControllerTest {

  @Mock private SessionService sessionService;

  @InjectMocks private SessionController sessionController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateSession() {
    SessionCreateRequest request = new SessionCreateRequest();
    request.setClassroomId(UUID.randomUUID());
    request.setStartTime(LocalDateTime.now());
    request.setEndTime(LocalDateTime.now().plusHours(1));
    request.setSoftSkillIds(Set.of(UUID.randomUUID()));
    request.setParticipantIds(Set.of(UUID.randomUUID()));

    Session session = new Session(); // Assuming a default constructor exists

    when(sessionService.createSession(
            request.getClassroomId(),
            request.getStartTime(),
            request.getEndTime(),
            request.getSoftSkillIds(),
            request.getParticipantIds()))
        .thenReturn(session);

    ResponseEntity<Session> response = sessionController.createSession(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(session, response.getBody());
  }
}
