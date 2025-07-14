package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.KidAnswer;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing KidAnswer entities.
 * Provides methods to perform CRUD operations and custom queries.
 * 
 * Author: Dimitrios Milios
 */
@Repository
public interface KidAnswerRepository extends JpaRepository<KidAnswer, UUID> {
  List<KidAnswer> findBySessionAndKid(Session session, User kid);
}
