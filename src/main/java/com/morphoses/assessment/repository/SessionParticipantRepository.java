package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.entity.SessionParticipant;
import com.morphoses.assessment.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing SessionParticipant entities. Provides methods to perform CRUD
 * operations and custom queries.
 *
 * <p>Author: Dimitrios Milios
 */
@Repository
public interface SessionParticipantRepository extends JpaRepository<SessionParticipant, UUID> {
  List<SessionParticipant> findBySession(Session session);

  Optional<SessionParticipant> findBySessionAndUser(Session session, User user);

  List<SessionParticipant> findBySessionAndUserUserTypeAndIsAbsentFalse(
      Session session, com.morphoses.assessment.util.UserType userType);
}
