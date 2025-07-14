package com.morphoses.assessment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

  @Test
  public void testHandleUserNotFound() {
    String message = "User not found";
    UserNotFoundException exception = new UserNotFoundException(message);
    ResponseEntity<String> response = globalHandler.handleUserNotFound(exception);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(message, response.getBody());
  }

  @Test
  public void testHandleSessionNotFound() {
    String message = "Session not found";
    SessionNotFoundException exception = new SessionNotFoundException(message);
    ResponseEntity<String> response = globalHandler.handleSessionNotFound(exception);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(message, response.getBody());
  }

  @Test
  public void testHandleClassroomNotFound() {
    String message = "Classroom not found";
    ClassroomNotFoundException exception = new ClassroomNotFoundException(message);
    ResponseEntity<String> response = globalHandler.handleClassroomNotFound(exception);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(message, response.getBody());
  }

  @Test
  public void testHandleIllegalArgument() {
    String message = "Illegal argument";
    IllegalArgumentException exception = new IllegalArgumentException(message);
    ResponseEntity<String> response = globalHandler.handleIllegalArgument(exception);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(message, response.getBody());
  }
}
