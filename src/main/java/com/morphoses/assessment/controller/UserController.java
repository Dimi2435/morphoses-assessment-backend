package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.UserCreateRequest;
import com.morphoses.assessment.dto.UserResponse;
import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.service.UserService;
import com.morphoses.assessment.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_BASE_PATH + AppConstants.USERS_ENDPOINT)
public class UserController {

  @Autowired private UserService userService;

  @Operation(summary = "Create a new user", description = "Creates a new user in the system")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping
  public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
    User user = userService.createUser(request.getUserType(), request.getName());
    return new ResponseEntity<>(
        new UserResponse(user.getId(), user.getUserType(), user.getName()), HttpStatus.CREATED);
  }

  @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
      })
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
    User user = userService.getUserById(id);
    return ResponseEntity.ok(new UserResponse(user.getId(), user.getUserType(), user.getName()));
  }
}
