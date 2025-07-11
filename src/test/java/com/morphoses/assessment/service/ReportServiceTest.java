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
class ReportServiceTest {

  @MockBean private SessionRepository sessionRepository;
  @MockBean private UserRepository userRepository;
  @MockBean private InstructorAnswerRepository instructorAnswerRepository;
  @MockBean private KidAnswerRepository kidAnswerRepository;
  @MockBean private SessionParticipantRepository sessionParticipantRepository;
  @InjectMocks private ReportService reportService;

  private Session session;
  private User kid;
  private User instructor;
  private SessionParticipant participant;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    session = new Session();
    session.setId(UUID.randomUUID());
    session.setCompleted(true);
    kid = new User();
    kid.setId(UUID.randomUUID());
    kid.setUserType(UserType.KID);
    instructor = new User();
    instructor.setId(UUID.randomUUID());
    instructor.setUserType(UserType.INSTRUCTOR);
    participant = new SessionParticipant();
    participant.setUser(kid);
    participant.setSession(session);
  }

  // @Test
  // void getReviewResultsForKid() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(kid.getId())).thenReturn(Optional.of(kid));
  //     when(sessionParticipantRepository.findBySessionAndUser(session,
  // kid)).thenReturn(Optional.of(participant));
  //     Map<String, Object> results = reportService.getReviewResultsForKid(session.getId(),
  // kid.getId());
  //     assertNotNull(results);
  //     assertEquals(kid.getId(), results.get("kidId"));
  // }

  // @Test
  // void getReviewResultsForKid_SessionNotFound() {
  //     when(sessionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // reportService.getReviewResultsForKid(UUID.randomUUID(), kid.getId()));
  // }

  // @Test
  // void getReviewResultsForKid_KidNotFound() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(kid.getId())).thenReturn(Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // reportService.getReviewResultsForKid(session.getId(), kid.getId()));
  // }

  // @Test
  // void getReviewResultsForKid_KidNotParticipant() {
  //     when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));
  //     when(userRepository.findById(kid.getId())).thenReturn(Optional.of(kid));
  //     when(sessionParticipantRepository.findBySessionAndUser(session,
  // kid)).thenReturn(Optional.empty());
  //     assertThrows(InvalidOperationException.class, () ->
  // reportService.getReviewResultsForKid(session.getId(), kid.getId()));
  // }
}
