package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.UserCreateRequest;
import com.morphoses.assessment.dto.UserResponse;
import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        User user = userService.createUser(request.getUserType(), request.getName());
        return new ResponseEntity<>(new UserResponse(user.getId(), user.getUserType(), user.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getUserType(), user.getName()));
    }
}