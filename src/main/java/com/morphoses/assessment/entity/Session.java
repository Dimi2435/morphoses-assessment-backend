package com.morphoses.assessment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "classroom_id", nullable = false)
  private Classroom classroom;

  @Column(name = "start_time", nullable = false)
  private LocalDateTime startTime;

  @Column(name = "end_time", nullable = false)
  private LocalDateTime endTime;

  @Column(name = "is_completed", nullable = false)
  private boolean isCompleted;

  @ManyToMany
  @JoinTable(
      name = "session_soft_skills",
      joinColumns = @JoinColumn(name = "session_id"),
      inverseJoinColumns = @JoinColumn(name = "soft_skill_id"))
  private Set<SoftSkill> softSkills = new HashSet<>();

  @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SessionParticipant> participants = new HashSet<>();

  public Session() {}

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Classroom getClassroom() {
    return classroom;
  }

  public void setClassroom(Classroom classroom) {
    this.classroom = classroom;
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

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }

  public Set<SoftSkill> getSoftSkills() {
    return softSkills;
  }

  public void setSoftSkills(Set<SoftSkill> softSkills) {
    this.softSkills = softSkills;
  }

  public Set<SessionParticipant> getParticipants() {
    return participants;
  }

  public void setParticipants(Set<SessionParticipant> participants) {
    this.participants = participants;
  }

  public void addParticipant(User user, boolean isAbsent) {
    SessionParticipant sessionParticipant = new SessionParticipant(this, user, isAbsent);
    this.participants.add(sessionParticipant);
  }

  public void removeParticipant(User user) {
    this.participants.removeIf(p -> p.getUser().equals(user));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Session session = (Session) o;
    return Objects.equals(id, session.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
