package com.morphoses.assessment.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity class representing a Classroom.
 * Contains information about the classroom, including its ID and name.
 * 
 * Author: Dimitrios Milios
 */
@Entity
@Table(name = "classrooms")
public class Classroom {
  @Id
  @GeneratedValue(
      strategy = GenerationType.AUTO) // Using AUTO for UUID generation, or define custom generator
  private UUID id;

  private String name;

  public Classroom() {}

  public Classroom(UUID id, String name) {
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
    Classroom classroom = (Classroom) o;
    return Objects.equals(id, classroom.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
