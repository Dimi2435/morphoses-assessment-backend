package com.morphoses.assessment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.repository.UserRepository;
import com.morphoses.assessment.util.UserType;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTest {

  @MockBean private UserRepository userRepository;
  @InjectMocks private UserService userService;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    user = new User();
    user.setId(UUID.randomUUID());
    user.setUserType(UserType.KID);
    user.setName("Test User");
  }

  // @Test
  // void createUser() {
  //     when(userRepository.save(any(User.class))).thenReturn(user);
  //     User createdUser = userService.createUser(UserType.KID, "Test User");
  //     assertNotNull(createdUser);
  //     //assertEquals(user.getName(), createdUser.getName());
  //     //verify(userRepository, times(1)).save(any(User.class));
  // }

  // @Test
  // void getUserById() {
  //     when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
  //     User foundUser = userService.getUserById(user.getId());
  //     assertNotNull(foundUser);
  //    // assertEquals(user.getId(), foundUser.getId());
  // }

  // @Test
  // void getUserById_NotFound() {
  //     when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // userService.getUserById(UUID.randomUUID()));
  // }
}
