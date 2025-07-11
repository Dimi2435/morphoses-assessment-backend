package com.morphoses.assessment.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClassroomCreateRequestTest {

  @Test
  void testGettersAndSetters() {
    ClassroomCreateRequest request = new ClassroomCreateRequest();
    String name = "Math Class";

    request.setName(name);

    assertEquals(name, request.getName());
  }
}
