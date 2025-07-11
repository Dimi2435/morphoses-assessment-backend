package com.morphoses.assessment.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "session_participants")
public class SessionParticipant implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "session_id", nullable = false)
  private Session session;

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "is_absent", nullable = false)
  private boolean isAbsent; // Only relevant for Kids [cite: 17]

  public SessionParticipant() {}

  public SessionParticipant(Session session, User user, boolean isAbsent) {
    this.session = session;
    this.user = user;
    this.isAbsent = isAbsent;
  }

  // Getters and Setters
  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
    return Objects.equals(session, that.session) && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(session, user);
  }
}
