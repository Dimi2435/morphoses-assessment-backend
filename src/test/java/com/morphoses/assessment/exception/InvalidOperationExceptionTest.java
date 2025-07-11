package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidOperationExceptionTest {

  @Test
  void testExceptionMessage() {
    String message = "Invalid operation";
    InvalidOperationException exception = new InvalidOperationException(message);

    assertEquals(message, exception.getMessage());
  }
}
