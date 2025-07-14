package com.morphoses.assessment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.entity.SoftSkill;
import com.morphoses.assessment.repository.SoftSkillRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SoftSkillServiceTest {

  @MockBean private SoftSkillRepository softSkillRepository;
  @InjectMocks private SoftSkillService softSkillService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void initSoftSkills_shouldSavePredefinedSkillsWhenNoneExist() {
    when(softSkillRepository.count()).thenReturn(0L);
    softSkillService.initSoftSkills();
    verify(softSkillRepository, times(3)).save(any(SoftSkill.class));
  }

  @Test
  void initSoftSkills_shouldNotSaveIfSkillsExist() {
    when(softSkillRepository.count()).thenReturn(5L);
    softSkillService.initSoftSkills();
    verify(softSkillRepository, never()).save(any(SoftSkill.class));
  }

  @Test
  void getAllSoftSkills_shouldReturnListOfSoftSkills() {
    List<SoftSkill> mockSkills =
        List.of(
            new SoftSkill(UUID.randomUUID(), "Teamwork"),
            new SoftSkill(UUID.randomUUID(), "Communication"));
    when(softSkillRepository.findAll()).thenReturn(mockSkills);
    List<SoftSkill> softSkills = softSkillService.getAllSoftSkills();
    assertEquals(2, softSkills.size());
    assertEquals("Teamwork", softSkills.get(0).getName());
    assertEquals("Communication", softSkills.get(1).getName());
  }

  @Test
  void getAllSoftSkills_shouldReturnEmptyListIfNoneExist() {
    when(softSkillRepository.findAll()).thenReturn(List.of());
    List<SoftSkill> softSkills = softSkillService.getAllSoftSkills();
    assertTrue(softSkills.isEmpty());
  }
}
