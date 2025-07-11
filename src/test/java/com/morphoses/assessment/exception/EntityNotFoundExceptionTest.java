package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EntityNotFoundExceptionTest {

  @Test
  void testExceptionMessage() {
    String message = "Entity not found";
    EntityNotFoundException exception = new EntityNotFoundException(message);

    assertEquals(message, exception.getMessage());
  }
}
