package com.morphoses.assessment.dto;

import com.morphoses.assessment.util.UserType;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private UserType userType;
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