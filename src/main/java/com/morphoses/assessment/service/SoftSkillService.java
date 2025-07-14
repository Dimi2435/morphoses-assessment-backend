package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.SoftSkill;
import com.morphoses.assessment.repository.SoftSkillRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing soft skills in the Morphoses Assessment application.
 *
 * <p>This class provides methods to initialize and retrieve soft skills.
 *
 * <p>Author: Dimitrios Milios
 */
@Service
public class SoftSkillService {

  @Autowired private SoftSkillRepository softSkillRepository;

  /**
   * Initializes predefined soft skills if none exist in the repository. This method is called after
   * the bean is constructed.
   */
  @PostConstruct
  public void initSoftSkills() {
    if (softSkillRepository.count() == 0) {
      // Example of predefined soft skills
      softSkillRepository.save(new SoftSkill(UUID.randomUUID(), "Teamwork"));
      softSkillRepository.save(new SoftSkill(UUID.randomUUID(), "Communication"));
      softSkillRepository.save(new SoftSkill(UUID.randomUUID(), "Problem-solving"));
      // ... add more of the 36 soft skills
    }
  }

  /**
   * Retrieves all soft skills from the repository.
   *
   * @return a list of all SoftSkill objects
   */
  public List<SoftSkill> getAllSoftSkills() {
    return softSkillRepository.findAll();
  }
}
