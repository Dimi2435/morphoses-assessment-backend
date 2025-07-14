package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.Session;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Session entities.
 * Provides methods to perform CRUD operations.
 * 
 * Author: Dimitrios Milios
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {}
