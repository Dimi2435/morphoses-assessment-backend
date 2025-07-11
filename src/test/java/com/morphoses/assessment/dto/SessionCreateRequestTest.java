package com.morphoses.assessment.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class SessionCreateRequestTest {

  @Test
  void testGettersAndSetters() {
    SessionCreateRequest request = new SessionCreateRequest();
    UUID classroomId = UUID.randomUUID();
    LocalDateTime startTime = LocalDateTime.now();
    LocalDateTime endTime = startTime.plusHours(1);
    Set<UUID> softSkillIds = new HashSet<>();
    Set<UUID> participantIds = new HashSet<>();

    request.setClassroomId(classroomId);
    request.setStartTime(startTime);
    request.setEndTime(endTime);
    request.setSoftSkillIds(softSkillIds);
    request.setParticipantIds(participantIds);

    assertEquals(classroomId, request.getClassroomId());
    assertEquals(startTime, request.getStartTime());
    assertEquals(endTime, request.getEndTime());
    assertEquals(softSkillIds, request.getSoftSkillIds());
    assertEquals(participantIds, request.getParticipantIds());
  }
}
