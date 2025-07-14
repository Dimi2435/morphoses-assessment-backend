package com.morphoses.assessment.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity class representing a Soft Skill. Contains information about the soft skill, including its
 * ID and name.
 *
 * <p>Author: Dimitrios Milios
 */
@Entity
@Table(name = "soft_skills")
public class SoftSkill {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;

  public SoftSkill() {}

  public SoftSkill(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SoftSkill softSkill = (SoftSkill) o;
    return Objects.equals(id, softSkill.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
