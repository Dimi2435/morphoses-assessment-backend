package com.morphoses.assessment.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class SoftSkillNotFoundExceptionTest {

  @Test
  public void testExceptionMessage() {
    String message = "Soft skill not found";

    // Assert that the exception is thrown with the correct message
    assertThatThrownBy(
            () -> {
              throw new SoftSkillNotFoundException(message);
            })
        .isInstanceOf(SoftSkillNotFoundException.class)
        .hasMessage(message);
  }
}
