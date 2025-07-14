package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalHandlerTest {

  @InjectMocks private GlobalHandler globalHandler;

  @Mock private InvalidOperationException invalidOperationException;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this); // Initialize mocks
  }

  @Test
  public void testHandleInvalidOperation() {
    String message = "Invalid operation";
    when(invalidOperationException.getMessage()).thenReturn(message);

    ResponseEntity<String> response =
        globalHandler.handleInvalidOperation(invalidOperationException);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(message, response.getBody());
  }
}
