package com.morphoses.assessment.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity class representing an Instructor's answer in a session. Contains information about the
 * session, instructor, kid, soft skill, and the answer given.
 *
 * <p>Author: Dimitrios Milios
 */
@Entity
@Table(name = "instructor_answers")
public class InstructorAnswer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "session_id", nullable = false)
  private Session session;

  @ManyToOne
  @JoinColumn(name = "instructor_id", nullable = false)
  private User instructor; // UserType INSTRUCTOR

  @ManyToOne
  @JoinColumn(name = "kid_id", nullable = false)
  private User kid; // UserType KID

  @ManyToOne
  @JoinColumn(name = "soft_skill_id", nullable = false)
  private SoftSkill softSkill;

  @Column(nullable = false)
  private Integer answer; // Scale of 1-5 [cite: 15]

  public InstructorAnswer() {}

  public InstructorAnswer(
      Session session, User instructor, User kid, SoftSkill softSkill, Integer answer) {
    this.session = session;
    this.instructor = instructor;
    this.kid = kid;
    this.softSkill = softSkill;
    this.answer = answer;
  }

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public User getInstructor() {
    return instructor;
  }

  public void setInstructor(User instructor) {
    this.instructor = instructor;
  }

  public User getKid() {
    return kid;
  }

  public void setKid(User kid) {
    this.kid = kid;
  }

  public SoftSkill getSoftSkill() {
    return softSkill;
  }

  public void setSoftSkill(SoftSkill softSkill) {
    this.softSkill = softSkill;
  }

  public Integer getAnswer() {
    return answer;
  }

  public void setAnswer(Integer answer) {
    if (answer < 1 || answer > 5) {
      throw new IllegalArgumentException("Answer must be between 1 and 5.");
    }
    this.answer = answer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InstructorAnswer that = (InstructorAnswer) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
