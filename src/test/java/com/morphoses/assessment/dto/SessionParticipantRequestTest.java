package com.morphoses.assessment.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class SessionParticipantRequestTest {

  @Test
  void testGettersAndSetters() {
    SessionParticipantRequest request = new SessionParticipantRequest();
    UUID userId = UUID.randomUUID();
    boolean isAbsent = true;

    request.setUserId(userId);
    request.setAbsent(isAbsent);

    assertEquals(userId, request.getUserId());
    assertEquals(isAbsent, request.isAbsent());
  }
}
