package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.InstructorAnswer;
import com.morphoses.assessment.entity.Session;
import com.morphoses.assessment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InstructorAnswerRepository extends JpaRepository<InstructorAnswer, UUID> {
    List<InstructorAnswer> findBySessionAndKid(Session session, User kid);
}