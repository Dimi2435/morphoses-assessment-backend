package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.ClassroomCreateRequest;
import com.morphoses.assessment.entity.Classroom;
import com.morphoses.assessment.service.ClassroomService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

  @Autowired private ClassroomService classroomService;

  @PostMapping
  public ResponseEntity<Classroom> createClassroom(@RequestBody ClassroomCreateRequest request) {
    Classroom classroom = classroomService.createClassroom(request.getName());
    return new ResponseEntity<>(classroom, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Classroom> getClassroomById(@PathVariable UUID id) {
    Classroom classroom = classroomService.getClassroomById(id);
    return ResponseEntity.ok(classroom);
  }
}
