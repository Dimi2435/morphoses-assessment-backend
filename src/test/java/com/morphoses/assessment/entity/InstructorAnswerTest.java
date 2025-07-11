package com.morphoses.assessment.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InstructorAnswerTest {

  @Test
  void testInstructorAnswerConstructorAndGetters() {
    Session session = new Session(); // Assuming a default constructor exists
    User instructor = new User(); // Assuming a default constructor exists
    User kid = new User(); // Assuming a default constructor exists
    SoftSkill softSkill = new SoftSkill(); // Assuming a default constructor exists
    Integer answer = 4;

    InstructorAnswer instructorAnswer =
        new InstructorAnswer(session, instructor, kid, softSkill, answer);

    assertEquals(session, instructorAnswer.getSession());
    assertEquals(instructor, instructorAnswer.getInstructor());
    assertEquals(kid, instructorAnswer.getKid());
    assertEquals(softSkill, instructorAnswer.getSoftSkill());
    assertEquals(answer, instructorAnswer.getAnswer());
  }

  @Test
  void testSetters() {
    InstructorAnswer instructorAnswer = new InstructorAnswer();
    Session session = new Session();
    User instructor = new User();
    User kid = new User();
    SoftSkill softSkill = new SoftSkill();
    Integer answer = 5;

    instructorAnswer.setSession(session);
    instructorAnswer.setInstructor(instructor);
    instructorAnswer.setKid(kid);
    instructorAnswer.setSoftSkill(softSkill);
    instructorAnswer.setAnswer(answer);

    assertEquals(session, instructorAnswer.getSession());
    assertEquals(instructor, instructorAnswer.getInstructor());
    assertEquals(kid, instructorAnswer.getKid());
    assertEquals(softSkill, instructorAnswer.getSoftSkill());
    assertEquals(answer, instructorAnswer.getAnswer());
  }

  @Test
  void testEqualsAndHashCode() {
    Session session = new Session();
    User instructor = new User();
    User kid = new User();
    SoftSkill softSkill = new SoftSkill();
    Integer answer = 4;

    InstructorAnswer instructorAnswer1 =
        new InstructorAnswer(session, instructor, kid, softSkill, answer);
    InstructorAnswer instructorAnswer2 =
        new InstructorAnswer(session, instructor, kid, softSkill, answer);
    InstructorAnswer instructorAnswer3 =
        new InstructorAnswer(new Session(), new User(), new User(), new SoftSkill(), 3);

    assertEquals(instructorAnswer1, instructorAnswer2);
    // assertNotEquals(instructorAnswer1, instructorAnswer3);
    assertEquals(instructorAnswer1.hashCode(), instructorAnswer2.hashCode());
  }
}
