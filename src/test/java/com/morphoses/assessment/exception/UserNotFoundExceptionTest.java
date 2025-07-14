package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserNotFoundExceptionTest {

  @Test
  public void testUserNotFoundExceptionMessage() {
    String expectedMessage = "User not found";
    UserNotFoundException exception = new UserNotFoundException(expectedMessage);
    assertEquals(expectedMessage, exception.getMessage());
  }
}
