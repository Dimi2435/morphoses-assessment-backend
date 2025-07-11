package com.morphoses.assessment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.dto.UserCreateRequest;
import com.morphoses.assessment.dto.UserResponse;
import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.service.UserService;
import com.morphoses.assessment.util.UserType;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UserControllerTest {

  @Mock private UserService userService;

  @InjectMocks private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateUser() {
    UserCreateRequest request = new UserCreateRequest();
    request.setUserType(UserType.INSTRUCTOR);
    request.setName("John Doe");

    User user = new User(); // Assuming a default constructor exists
    user.setId(UUID.randomUUID());
    user.setUserType(request.getUserType());
    user.setName(request.getName());

    when(userService.createUser(request.getUserType(), request.getName())).thenReturn(user);

    ResponseEntity<UserResponse> response = userController.createUser(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // assertEquals(new UserResponse(user.getId(), user.getUserType(), user.getName()),
    // response.getBody());
  }

  @Test
  void testGetUserById() {
    UUID id = UUID.randomUUID();
    User user = new User(); // Assuming a default constructor exists
    user.setId(id);
    user.setUserType(UserType.KID);
    user.setName("Jane Doe");

    when(userService.getUserById(id)).thenReturn(user);

    ResponseEntity<UserResponse> response = userController.getUserById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    // assertEquals(new UserResponse(user.getId(), user.getUserType(), user.getName()),
    // response.getBody());
  }
}
