package com.morphoses.assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Schema(description = "Request object for creating a session")
public class SessionCreateRequest {
  @Schema(description = "The ID of the classroom for the session")
  private UUID classroomId;

  @Schema(description = "The start time of the session")
  private LocalDateTime startTime;

  @Schema(description = "The end time of the session")
  private LocalDateTime endTime;

  @Schema(description = "A set of SoftSkill IDs associated with the session")
  private Set<UUID> softSkillIds;

  @Schema(description = "A set of participant IDs for the session")
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
