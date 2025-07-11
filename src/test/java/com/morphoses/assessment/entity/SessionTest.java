package com.morphoses.assessment.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SessionTest {

  @Test
  void testSessionConstructorAndGetters() {
    Classroom classroom = new Classroom(); // Assuming a default constructor exists
    LocalDateTime startTime = LocalDateTime.now();
    LocalDateTime endTime = startTime.plusHours(1);
    boolean isCompleted = false;
    Set<SoftSkill> softSkills = new HashSet<>();

    Session session = new Session();
    session.setClassroom(classroom);
    session.setStartTime(startTime);
    session.setEndTime(endTime);
    session.setCompleted(isCompleted);
    session.setSoftSkills(softSkills);

    assertEquals(classroom, session.getClassroom());
    assertEquals(startTime, session.getStartTime());
    assertEquals(endTime, session.getEndTime());
    assertEquals(isCompleted, session.isCompleted());
    assertEquals(softSkills, session.getSoftSkills());
  }

  @Test
  void testSetters() {
    Session session = new Session();
    Classroom classroom = new Classroom();
    LocalDateTime startTime = LocalDateTime.now();
    LocalDateTime endTime = startTime.plusHours(1);
    boolean isCompleted = true;

    session.setClassroom(classroom);
    session.setStartTime(startTime);
    session.setEndTime(endTime);
    session.setCompleted(isCompleted);

    assertEquals(classroom, session.getClassroom());
    assertEquals(startTime, session.getStartTime());
    assertEquals(endTime, session.getEndTime());
    assertEquals(isCompleted, session.isCompleted());
  }

  @Test
  void testEqualsAndHashCode() {
    Session session1 = new Session();
    Session session2 = new Session();

    assertEquals(session1, session2);
    assertEquals(session1.hashCode(), session2.hashCode());

    // session1.setClassroom(new Classroom());
    // assertNotEquals(session1, session2);
  }
}
