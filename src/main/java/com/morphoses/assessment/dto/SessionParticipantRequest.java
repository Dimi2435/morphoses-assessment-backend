package com.morphoses.assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * Request object for session participant.
 * Contains the user ID and absence status.
 * 
 * Author: Dimitrios Milios
 */
@Schema(description = "Request object for session participant")
public class SessionParticipantRequest {
  @Schema(description = "The ID of the user participating in the session")
  private UUID userId;

  @Schema(description = "Indicates if the participant is absent")
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
