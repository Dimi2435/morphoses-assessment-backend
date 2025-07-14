package com.morphoses.assessment.entity;

import com.morphoses.assessment.util.UserType;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity class representing a User. Contains information about the user, including their ID, type,
 * and name.
 *
 * <p>Author: Dimitrios Milios
 */
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_type", nullable = false)
  private UserType userType;

  private String name;

  public User() {}

  public User(UUID id, UserType userType, String name) {
    this.id = id;
    this.userType = userType;
    this.name = name;
  }

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
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
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
