package com.morphoses.assessment.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class SessionCreateRequest {
    private UUID classroomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<UUID> softSkillIds;
    private Set<UUID> participantIds; // Includes both kids and instructors

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Set<UUID> getSoftSkillIds() {
        return softSkillIds;
    }

    public void setSoftSkillIds(Set<UUID> softSkillIds) {
        this.softSkillIds = softSkillIds;
    }

    public Set<UUID> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(Set<UUID> participantIds) {
        this.participantIds = participantIds;
    }
}