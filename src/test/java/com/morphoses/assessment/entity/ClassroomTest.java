package com.morphoses.assessment.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ClassroomTest {

  @Test
  void testClassroomConstructorAndGetters() {
    UUID id = UUID.randomUUID();
    String name = "Math Class";
    Classroom classroom = new Classroom(id, name);

    assertEquals(id, classroom.getId());
    assertEquals(name, classroom.getName());
  }

  @Test
  void testSetters() {
    Classroom classroom = new Classroom();
    UUID id = UUID.randomUUID();
    String name = "Science Class";

    classroom.setId(id);
    classroom.setName(name);

    assertEquals(id, classroom.getId());
    assertEquals(name, classroom.getName());
  }

  @Test
  void testEqualsAndHashCode() {
    UUID id = UUID.randomUUID();
    Classroom classroom1 = new Classroom(id, "Class A");
    Classroom classroom2 = new Classroom(id, "Class B");
    Classroom classroom3 = new Classroom(UUID.randomUUID(), "Class C");

    assertEquals(classroom1, classroom2);
    assertNotEquals(classroom1, classroom3);
    assertEquals(classroom1.hashCode(), classroom2.hashCode());
  }
}
