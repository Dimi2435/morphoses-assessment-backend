package com.morphoses.assessment.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class SessionParticipantIdTest {

  @Test
  public void testEqualsAndHashCode() {
    UUID sessionId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    SessionParticipantId id1 = new SessionParticipantId(sessionId, userId);
    SessionParticipantId id2 = new SessionParticipantId(sessionId, userId);
    SessionParticipantId id3 = new SessionParticipantId(UUID.randomUUID(), UUID.randomUUID());

    // Test equality
    assertThat(id1).isEqualTo(id2); // Should be equal
    assertThat(id1).isNotEqualTo(id3); // Should not be equal

    // Test hashCode
    assertThat(id1.hashCode()).isEqualTo(id2.hashCode()); // Should have the same hash code
    assertThat(id1.hashCode()).isNotEqualTo(id3.hashCode()); // Should have different hash codes
  }

  @Test
  public void testConstructor() {
    UUID sessionId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    SessionParticipantId id = new SessionParticipantId(sessionId, userId);

    assertThat(id.getSessionId()).isEqualTo(sessionId);
    assertThat(id.getUserId()).isEqualTo(userId);
  }
}
