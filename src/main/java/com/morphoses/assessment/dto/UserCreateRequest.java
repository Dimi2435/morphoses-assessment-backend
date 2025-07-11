package com.morphoses.assessment.dto;

import com.morphoses.assessment.util.UserType;

public class UserCreateRequest {
  private UserType userType;
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
