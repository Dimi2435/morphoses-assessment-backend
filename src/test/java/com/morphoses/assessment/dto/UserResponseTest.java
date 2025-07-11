package com.morphoses.assessment.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.morphoses.assessment.util.UserType;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserResponseTest {

  @Test
  void testGettersAndSetters() {
    UUID id = UUID.randomUUID();
    UserType userType = UserType.INSTRUCTOR; // Assuming UserType has this value
    String name = "John Doe";

    UserResponse response = new UserResponse(id, userType, name);

    assertEquals(id, response.getId());
    assertEquals(userType, response.getUserType());
    assertEquals(name, response.getName());
  }
}
