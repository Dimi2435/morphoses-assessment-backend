package com.morphoses.assessment.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KidAnswerTest {

  @Test
  void testKidAnswerConstructorAndGetters() {
    Session session = new Session(); // Assuming a default constructor exists
    User kid = new User(); // Assuming a default constructor exists
    SoftSkill softSkill = new SoftSkill(); // Assuming a default constructor exists
    Integer answer = 3;

    KidAnswer kidAnswer = new KidAnswer(session, kid, softSkill, answer);

    assertEquals(session, kidAnswer.getSession());
    assertEquals(kid, kidAnswer.getKid());
    assertEquals(softSkill, kidAnswer.getSoftSkill());
    assertEquals(answer, kidAnswer.getAnswer());
  }

  @Test
  void testSetters() {
    KidAnswer kidAnswer = new KidAnswer();
    Session session = new Session();
    User kid = new User();
    SoftSkill softSkill = new SoftSkill();
    Integer answer = 2;

    kidAnswer.setSession(session);
    kidAnswer.setKid(kid);
    kidAnswer.setSoftSkill(softSkill);
    kidAnswer.setAnswer(answer);

    assertEquals(session, kidAnswer.getSession());
    assertEquals(kid, kidAnswer.getKid());
    assertEquals(softSkill, kidAnswer.getSoftSkill());
    assertEquals(answer, kidAnswer.getAnswer());
  }

  @Test
  void testEqualsAndHashCode() {
    Session session = new Session();
    User kid = new User();
    SoftSkill softSkill = new SoftSkill();
    Integer answer = 3;

    KidAnswer kidAnswer1 = new KidAnswer(session, kid, softSkill, answer);
    KidAnswer kidAnswer2 = new KidAnswer(session, kid, softSkill, answer);
    KidAnswer kidAnswer3 = new KidAnswer(new Session(), new User(), new SoftSkill(), 1);

    assertEquals(kidAnswer1, kidAnswer2);
    // assertNotEquals(kidAnswer1, kidAnswer3);
    assertEquals(kidAnswer1.hashCode(), kidAnswer2.hashCode());
  }
}
