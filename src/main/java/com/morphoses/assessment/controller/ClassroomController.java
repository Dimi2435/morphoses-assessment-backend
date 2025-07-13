package com.morphoses.assessment.controller;

import com.morphoses.assessment.dto.ClassroomCreateRequest;
import com.morphoses.assessment.entity.Classroom;
import com.morphoses.assessment.service.ClassroomService;
import com.morphoses.assessment.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.CLASSROOMS_ENDPOINT)
public class ClassroomController {

  @Autowired private ClassroomService classroomService;

  @Operation(
      summary = "Create a new classroom",
      description = "Creates a new classroom in the system")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Classroom created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping
  public ResponseEntity<Classroom> createClassroom(@RequestBody ClassroomCreateRequest request) {
    Classroom classroom = classroomService.createClassroom(request.getName());
    return new ResponseEntity<>(classroom, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Get classroom by ID",
      description = "Retrieves a classroom by its unique ID.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Classroom found"),
        @ApiResponse(responseCode = "404", description = "Classroom not found")
      })
  @GetMapping("/{id}")
  public ResponseEntity<Classroom> getClassroomById(@PathVariable UUID id) {
    Classroom classroom = classroomService.getClassroomById(id);
    return ResponseEntity.ok(classroom);
  }
}
