package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.InstructorAnswer;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing InstructorAnswer entities. Provides methods to perform CRUD
 * operations and custom queries.
 *
 * <p>Author: Dimitrios Milios
 */
@Repository
public interface InstructorAnswerRepository extends JpaRepository<InstructorAnswer, UUID> {
  List<InstructorAnswer> findBySessionAndKid(Session session, User kid);
}
