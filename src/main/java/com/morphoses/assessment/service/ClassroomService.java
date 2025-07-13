package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.Classroom;
import com.morphoses.assessment.exception.ClassroomNotFoundException;
import com.morphoses.assessment.repository.ClassroomRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

  @Autowired private ClassroomRepository classroomRepository;

  public Classroom createClassroom(String name) {
    Classroom classroom = new Classroom();
    classroom.setName(name);
    return classroomRepository.save(classroom);
  }

  public Classroom getClassroomById(UUID id) {
    return classroomRepository
        .findById(id)
        .orElseThrow(() -> new ClassroomNotFoundException("Classroom not found with ID: " + id));
  }
}
