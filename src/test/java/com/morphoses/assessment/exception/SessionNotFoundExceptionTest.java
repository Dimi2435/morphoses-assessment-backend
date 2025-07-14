package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SessionNotFoundExceptionTest {

  @Test
  public void testSessionNotFoundExceptionMessage() {
    String expectedMessage = "Session not found";
    SessionNotFoundException exception = new SessionNotFoundException(expectedMessage);
    assertEquals(expectedMessage, exception.getMessage());
  }
}
