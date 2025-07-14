package com.morphoses.assessment.dto;

import com.morphoses.assessment.util.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * Response object for user information.
 * Contains the user's ID, type, and name.
 * 
 * Author: Dimitrios Milios
 */
@Schema(description = "Response object for user information")
public class UserResponse {
  @Schema(description = "The unique identifier of the user")
  private UUID id;

  @Schema(description = "The type of user")
  private UserType userType;

  @Schema(description = "The name of the user")
  private String name;

  public UserResponse(UUID id, UserType userType, String name) {
    this.id = id;
    this.userType = userType;
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
