package com.morphoses.assessment.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SessionParticipantTest {

  @Test
  void testSessionParticipantConstructorAndGetters() {
    Session session = new Session(); // Assuming a default constructor exists
    User user = new User(); // Assuming a default constructor exists
    boolean isAbsent = false;

    SessionParticipant participant = new SessionParticipant(session, user, isAbsent);

    assertEquals(session, participant.getSession());
    assertEquals(user, participant.getUser());
    assertEquals(isAbsent, participant.isAbsent());
  }

  @Test
  void testSetters() {
    SessionParticipant participant = new SessionParticipant();
    Session session = new Session();
    User user = new User();
    boolean isAbsent = true;

    participant.setSession(session);
    participant.setUser(user);
    participant.setAbsent(isAbsent);

    assertEquals(session, participant.getSession());
    assertEquals(user, participant.getUser());
    assertEquals(isAbsent, participant.isAbsent());
  }

  @Test
  void testEqualsAndHashCode() {
    Session session = new Session();
    User user = new User();
    SessionParticipant participant1 = new SessionParticipant(session, user, false);
    SessionParticipant participant2 = new SessionParticipant(session, user, false);

    assertEquals(participant1, participant2);
    assertEquals(participant1.hashCode(), participant2.hashCode());

    // participant1.setAbsent(true);
    // assertNotEquals(participant1, participant2);
  }
}
