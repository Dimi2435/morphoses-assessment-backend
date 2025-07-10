package com.morphoses.assessment.dto;

import java.util.UUID;

public class SessionParticipantRequest {
    private UUID userId;
    private boolean isAbsent; // Only relevant for Kids

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isAbsent() {
        return isAbsent;
    }

    public void setAbsent(boolean absent) {
        isAbsent = absent;
    }
}