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
class QuestionServiceTest {

  @MockBean private SessionRepository sessionRepository;
  @MockBean private UserRepository userRepository;
  @MockBean private SoftSkillRepository softSkillRepository;
  @MockBean private SessionParticipantRepository sessionParticipantRepository;
  @InjectMocks private QuestionService questionService;

  private Session session;
  private User instructor;
  private User kid;
  private SessionParticipant participant;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    session = new Session();
    session.setId(UUID.randomUUID());
    session.setCompleted(false);
    instructor = new User();
    instructor.setId(UUID.randomUUID());
    instructor.setUserType(UserType.INSTRUCTOR);
    kid = new User();
    kid.setId(UUID.randomUUID());
    kid.setUserType(UserType.KID);
    participant = new SessionParticipant();
    participant.setUser(kid);
    participant.setSession(session);
  }

  // @Test
  // void getRelevantQuestionsForUser_Instructor() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(instructor.getId())).thenReturn(Optional.of(instructor));
  //     when(sessionParticipantRepository.findBySessionAndUser(session,
  // instructor)).thenReturn(Optional.of(participant));
  //     List<String> questions = questionService.getRelevantQuestionsForUser(instructor.getId(),
  // session.getId());
  //     assertFalse(questions.isEmpty());
  // }

  // @Test
  // void getRelevantQuestionsForUser_Kid() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(kid.getId())).thenReturn(Optional.of(kid));
  //     when(sessionParticipantRepository.findBySessionAndUser(session,
  // kid)).thenReturn(Optional.of(participant));
  //     List<String> questions = questionService.getRelevantQuestionsForUser(kid.getId(),
  // session.getId());
  //     assertFalse(questions.isEmpty());
  // }

  // @Test
  // void getRelevantQuestionsForUser_SessionNotFound() {
  //     when(sessionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // questionService.getRelevantQuestionsForUser(kid.getId(), UUID.randomUUID()));
  // }

  // @Test
  // void getRelevantQuestionsForUser_UserNotParticipant() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(kid.getId())).thenReturn(Optional.of(kid));
  //     when(sessionParticipantRepository.findBySessionAndUser(session,
  // kid)).thenReturn(Optional.empty());
  //     assertThrows(InvalidOperationException.class, () ->
  // questionService.getRelevantQuestionsForUser(kid.getId(), session.getId()));
  // }
}
