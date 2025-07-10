package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkill, UUID> {
}