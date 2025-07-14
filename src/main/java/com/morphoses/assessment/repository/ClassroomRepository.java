package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.Classroom;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Classroom entities. Provides methods to perform CRUD
 * operations.
 *
 * <p>Author: Dimitrios Milios
 */
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {}
