package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.util.UserType;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing User entities.
 * Provides methods to perform CRUD operations and custom queries.
 * 
 * Author: Dimitrios Milios
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  List<User> findByUserType(UserType userType);
}
