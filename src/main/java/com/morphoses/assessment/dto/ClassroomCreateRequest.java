package com.morphoses.assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request object for creating a classroom. Contains the name of the classroom.
 *
 * <p>Author: Dimitrios Milios
 */
@Schema(description = "Request object for creating a classroom")
public class ClassroomCreateRequest {
  @Schema(description = "The name of the classroom")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
