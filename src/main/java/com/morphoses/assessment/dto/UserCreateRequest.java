package com.morphoses.assessment.dto;

import com.morphoses.assessment.util.UserType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for creating a user")
public class UserCreateRequest {
  @Schema(description = "The type of user")
  private UserType userType;

  @Schema(description = "The name of the user")
  private String name;

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
