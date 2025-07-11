package com.morphoses.assessment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.entity.*;
import com.morphoses.assessment.repository.*;
import com.morphoses.assessment.util.UserType;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SessionServiceTest {

  @MockBean private SessionRepository sessionRepository;
  @MockBean private ClassroomRepository classroomRepository;
  @MockBean private SoftSkillRepository softSkillRepository;
  @MockBean private UserRepository userRepository;
  @MockBean private SessionParticipantRepository sessionParticipantRepository;
  @InjectMocks private SessionService sessionService;

  private Session session;
  private Classroom classroom;
  private User instructor;
  private User kid;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    classroom = new Classroom();
    classroom.setId(UUID.randomUUID());
    classroom.setName("Test Classroom");
    session = new Session();
    session.setId(UUID.randomUUID());
    session.setClassroom(classroom);
    session.setCompleted(false);
    instructor = new User();
    instructor.setId(UUID.randomUUID());
    instructor.setUserType(UserType.INSTRUCTOR);
    kid = new User();
    kid.setId(UUID.randomUUID());
    kid.setUserType(UserType.KID);
  }

  // @Test
  // void createSession() {
  //     when(classroomRepository.findById(classroom.getId())).thenReturn(Optional.of(classroom));
  //     when(softSkillRepository.findById(any(UUID.class))).thenReturn(Optional.of(new
  // SoftSkill()));
  //     when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(instructor));
  //     Session createdSession = sessionService.createSession(classroom.getId(),
  // LocalDateTime.now(), LocalDateTime.now().plusHours(1), Set.of(UUID.randomUUID()),
  // Set.of(instructor.getId(), kid.getId()));
  //     assertNotNull(createdSession);
  //     assertEquals(classroom.getId(), createdSession.getClassroom().getId());
  // }

  // @Test
  // void createSession_ClassroomNotFound() {
  //     when(classroomRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // sessionService.createSession(UUID.randomUUID(), LocalDateTime.now(),
  // LocalDateTime.now().plusHours(1), Set.of(UUID.randomUUID()), Set.of(instructor.getId(),
  // kid.getId())));
  // }

  // @Test
  // void completeSession() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(instructor.getId())).thenReturn(Optional.of(instructor));
  //     Session completedSession = sessionService.completeSession(session.getId(),
  // instructor.getId());
  //     assertTrue(completedSession.isCompleted());
  // }

  // @Test
  // void completeSession_SessionNotFound() {
  //     when(sessionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // sessionService.completeSession(UUID.randomUUID(), instructor.getId()));
  // }

  // @Test
  // void completeSession_InstructorNotFound() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(instructor.getId())).thenReturn(Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // sessionService.completeSession(session.getId(), instructor.getId()));
  // }
}
