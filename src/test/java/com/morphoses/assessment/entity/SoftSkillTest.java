package com.morphoses.assessment.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class SoftSkillTest {

  @Test
  void testSoftSkillConstructorAndGetters() {
    UUID id = UUID.randomUUID();
    String name = "Communication";

    SoftSkill softSkill = new SoftSkill(id, name);

    assertEquals(id, softSkill.getId());
    assertEquals(name, softSkill.getName());
  }

  @Test
  void testSetters() {
    SoftSkill softSkill = new SoftSkill();
    UUID id = UUID.randomUUID();
    String name = "Teamwork";

    softSkill.setId(id);
    softSkill.setName(name);

    assertEquals(id, softSkill.getId());
    assertEquals(name, softSkill.getName());
  }

  @Test
  void testEqualsAndHashCode() {
    UUID id = UUID.randomUUID();
    SoftSkill softSkill1 = new SoftSkill(id, "Skill A");
    SoftSkill softSkill2 = new SoftSkill(id, "Skill B");
    SoftSkill softSkill3 = new SoftSkill(UUID.randomUUID(), "Skill C");

    assertEquals(softSkill1, softSkill2);
    assertNotEquals(softSkill1, softSkill3);
    assertEquals(softSkill1.hashCode(), softSkill2.hashCode());
  }
}
