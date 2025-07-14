// src/main/java/com/morphoses/assessment/entity/SessionParticipantId.java
package com.morphoses.assessment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class SessionParticipantId implements Serializable {

  @Column(name = "session_id", nullable = false)
  private UUID sessionId;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  public SessionParticipantId() {}

  public SessionParticipantId(UUID sessionId, UUID userId) {
    this.sessionId = sessionId;
    this.userId = userId;
  }

  public UUID getSessionId() {
    return sessionId;
  }

  public void setSessionId(UUID sessionId) {
    this.sessionId = sessionId;
  }

  public UUID getUserId() {
    return userId;
  }

  // FIX THIS LINE: Removed the extra 'void'
  public void setUserId(UUID userId) { // <<< Corrected line
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SessionParticipantId that = (SessionParticipantId) o;
    return Objects.equals(sessionId, that.sessionId) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionId, userId);
  }
}
