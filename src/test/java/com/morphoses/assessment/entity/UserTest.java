package com.morphoses.assessment.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.morphoses.assessment.util.UserType;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void testUserConstructorAndGetters() {
    UUID id = UUID.randomUUID();
    UserType userType = UserType.INSTRUCTOR; // Assuming UserType has this value
    String name = "John Doe";

    User user = new User(id, userType, name);

    assertEquals(id, user.getId());
    assertEquals(userType, user.getUserType());
    assertEquals(name, user.getName());
  }

  @Test
  void testSetters() {
    User user = new User();
    UUID id = UUID.randomUUID();
    UserType userType = UserType.KID; // Assuming UserType has this value
    String name = "Jane Doe";

    user.setId(id);
    user.setUserType(userType);
    user.setName(name);

    assertEquals(id, user.getId());
    assertEquals(userType, user.getUserType());
    assertEquals(name, user.getName());
  }

  @Test
  void testEqualsAndHashCode() {
    UUID id = UUID.randomUUID();
    User user1 = new User(id, UserType.INSTRUCTOR, "User A");
    User user2 = new User(id, UserType.INSTRUCTOR, "User B");
    User user3 = new User(UUID.randomUUID(), UserType.KID, "User C");

    assertEquals(user1, user2);
    assertNotEquals(user1, user3);
    assertEquals(user1.hashCode(), user2.hashCode());
  }
}
