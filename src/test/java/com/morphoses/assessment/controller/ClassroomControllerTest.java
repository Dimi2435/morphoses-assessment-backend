package com.morphoses.assessment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.dto.ClassroomCreateRequest;
import com.morphoses.assessment.entity.Classroom;
import com.morphoses.assessment.service.ClassroomService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ClassroomControllerTest {

  @Mock private ClassroomService classroomService;

  @InjectMocks private ClassroomController classroomController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateClassroom() {
    ClassroomCreateRequest request = new ClassroomCreateRequest();
    request.setName("Math Class");
    Classroom classroom = new Classroom(UUID.randomUUID(), "Math Class");

    when(classroomService.createClassroom(request.getName())).thenReturn(classroom);

    ResponseEntity<Classroom> response = classroomController.createClassroom(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(classroom, response.getBody());
  }

  @Test
  void testGetClassroomById() {
    UUID id = UUID.randomUUID();
    Classroom classroom = new Classroom(id, "Math Class");

    when(classroomService.getClassroomById(id)).thenReturn(classroom);

    ResponseEntity<Classroom> response = classroomController.getClassroomById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(classroom, response.getBody());
  }
}
