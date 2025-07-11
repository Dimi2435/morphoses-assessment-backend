package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.SoftSkill;
import com.morphoses.assessment.repository.SoftSkillRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoftSkillService {

  @Autowired private SoftSkillRepository softSkillRepository;

  // This could be removed if DataInitializer handles all prepopulation
  @PostConstruct
  public void initSoftSkills() {
    if (softSkillRepository.count() == 0) {
      // Example of predefined soft skills [cite: 3]
      softSkillRepository.save(new SoftSkill(UUID.randomUUID(), "Teamwork"));
      softSkillRepository.save(new SoftSkill(UUID.randomUUID(), "Communication"));
      softSkillRepository.save(new SoftSkill(UUID.randomUUID(), "Problem-solving"));
      // ... add more of the 36 soft skills
    }
  }

  public List<SoftSkill> getAllSoftSkills() {
    return softSkillRepository.findAll();
  }
}
