package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClassroomNotFoundExceptionTest {

  @Test
  public void testClassroomNotFoundExceptionMessage() {
    String expectedMessage = "Classroom not found";
    ClassroomNotFoundException exception = new ClassroomNotFoundException(expectedMessage);
    assertEquals(expectedMessage, exception.getMessage());
  }
}
