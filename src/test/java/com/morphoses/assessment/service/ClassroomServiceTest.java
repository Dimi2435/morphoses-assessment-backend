package com.morphoses.assessment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.entity.Classroom;
import com.morphoses.assessment.repository.ClassroomRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ClassroomServiceTest {

  @MockBean private ClassroomRepository classroomRepository;

  @InjectMocks private ClassroomService classroomService;

  private Classroom classroom;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    classroom = new Classroom();
    classroom.setId(UUID.randomUUID());
    classroom.setName("Test Classroom");
  }

  // @Test
  // void createClassroom() {
  //     when(classroomRepository.save(any(Classroom.class))).thenReturn(classroom);
  //     Classroom createdClassroom = classroomService.createClassroom("Test Classroom");
  //     assertNotNull(createdClassroom);
  //     assertEquals(classroom.getName(), createdClassroom.getName());
  //     verify(classroomRepository, times(1)).save(any(Classroom.class));
  // }

  // @Test
  // void getClassroomById() {
  //     when(classroomRepository.findById(classroom.getId())).thenReturn(Optional.of(classroom));
  //     Classroom foundClassroom = classroomService.getClassroomById(classroom.getId());
  //     assertNotNull(foundClassroom);
  //     //assertEquals(classroom.getId(), foundClassroom.getId());
  // }

  // @Test
  // void getClassroomById_NotFound() {
  //     when(classroomRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
  //     assertThrows(EntityNotFoundException.class, () ->
  // classroomService.getClassroomById(UUID.randomUUID()));
  // }
}
