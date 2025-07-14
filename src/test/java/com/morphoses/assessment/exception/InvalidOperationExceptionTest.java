package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidOperationExceptionTest {

  @Test
  public void testInvalidOperationExceptionMessage() {
    String expectedMessage = "Invalid operation";
    InvalidOperationException exception = new InvalidOperationException(expectedMessage);
    assertEquals(expectedMessage, exception.getMessage());
  }
}
