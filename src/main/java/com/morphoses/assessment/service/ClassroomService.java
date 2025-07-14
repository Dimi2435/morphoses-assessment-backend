package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.Classroom;
import com.morphoses.assessment.exception.ClassroomNotFoundException;
import com.morphoses.assessment.repository.ClassroomRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing classrooms in the Morphoses Assessment application.
 *
 * <p>This class provides methods to create and retrieve classrooms.
 *
 * <p>Author: Dimitrios Milios
 */
@Service
public class ClassroomService {

  @Autowired private ClassroomRepository classroomRepository;

  /**
   * Creates a new classroom with the specified name.
   *
   * @param name the name of the classroom
   * @return the created Classroom object
   */
  public Classroom createClassroom(String name) {
    Classroom classroom = new Classroom();
    classroom.setName(name);
    return classroomRepository.save(classroom);
  }

  /**
   * Retrieves a classroom by its unique ID.
   *
   * @param id the unique ID of the classroom
   * @return the Classroom object associated with the given ID
   * @throws ClassroomNotFoundException if no classroom is found with the given ID
   */
  public Classroom getClassroomById(UUID id) {
    return classroomRepository
        .findById(id)
        .orElseThrow(() -> new ClassroomNotFoundException("Classroom not found with ID: " + id));
  }
}
