package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.Session;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {}
