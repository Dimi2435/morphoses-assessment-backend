// src/main/java/com/morphoses/assessment/entity/SessionParticipant.java
package com.morphoses.assessment.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects; // Keep this for equals/hashCode if you decide to keep them in entity,

// though not strictly needed here

/**
 * Entity class representing a participant in a session. Contains information about the session,
 * user, and absence status.
 *
 * <p>Author: Dimitrios Milios
 */
@Entity
@Table(name = "session_participants")
public class SessionParticipant implements Serializable {

  @EmbeddedId // <<< This is the composite primary key
  private SessionParticipantId id; // This field holds the composite ID

  // @MapsId("sessionId") tells JPA that this relationship maps to the 'sessionId' field in the
  // @EmbeddedId
  @ManyToOne(fetch = FetchType.LAZY) // Use LAZY fetch to avoid eager loading issues
  @MapsId("sessionId") // This maps 'session' to 'sessionId' in the embedded ID
  @JoinColumn(
      name = "session_id",
      insertable = false,
      updatable = false) // These columns are handled by @MapsId
  private Session session;

  // @MapsId("userId") tells JPA that this relationship maps to the 'userId' field in the
  // @EmbeddedId
  @ManyToOne(fetch = FetchType.LAZY) // Use LAZY fetch
  @MapsId("userId") // This maps 'user' to 'userId' in the embedded ID
  @JoinColumn(
      name = "user_id",
      insertable = false,
      updatable = false) // These columns are handled by @MapsId
  private User user;

  @Column(name = "is_absent", nullable = false)
  private boolean isAbsent;

  // Constructors
  public SessionParticipant() {
    this.id = new SessionParticipantId(); // Initialize the embedded ID
  }

  // This constructor would be more useful if creating SessionParticipant from scratch
  public SessionParticipant(Session session, User user, boolean isAbsent) {
    this.session = session;
    this.user = user;
    this.isAbsent = isAbsent;
    // The ID should be set based on the session and user IDs if they are already available
    // For new entities, you'd typically set the ID after the session/user objects have their IDs.
    // A more practical constructor for setting up new entities might look like this:
    this.id =
        new SessionParticipantId(
            session.getId(), user.getId()); // Assuming Session and User have getId()
  }

  // Getter for the composite ID
  public SessionParticipantId getId() {
    return id;
  }

  public void setId(SessionParticipantId id) {
    this.id = id;
  }

  // Getters and Setters for relationships and other fields
  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
    // When setting the session, also update the embedded ID's sessionId
    if (this.id == null) {
      this.id = new SessionParticipantId();
    }
    if (session != null) {
      this.id.setSessionId(session.getId());
    }
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
    // When setting the user, also update the embedded ID's userId
    if (this.id == null) {
      this.id = new SessionParticipantId();
    }
    if (user != null) {
      this.id.setUserId(user.getId());
    }
  }

  public boolean isAbsent() {
    return isAbsent;
  }

  public void setAbsent(boolean absent) {
    isAbsent = absent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SessionParticipant that = (SessionParticipant) o;
    // Comparison should be based on the ID, not the entire relationship objects
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id); // Hash code should be based on the ID
  }
}
