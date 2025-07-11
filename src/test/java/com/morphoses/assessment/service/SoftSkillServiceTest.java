package com.morphoses.assessment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.morphoses.assessment.repository.SoftSkillRepository;
import org.junit.jupiter.api.BeforeEach;
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

  // @Test
  // void initSoftSkills() {
  //     when(softSkillRepository.count()).thenReturn(0L);
  //     softSkillService.initSoftSkills();
  //     verify(softSkillRepository, times(3)).save(any(SoftSkill.class));
  // }

  // @Test
  // void getAllSoftSkills() {
  //     when(softSkillRepository.findAll()).thenReturn(List.of(new SoftSkill(UUID.randomUUID(),
  // "Teamwork"), new SoftSkill(UUID.randomUUID(), "Communication")));
  //     List<SoftSkill> softSkills = softSkillService.getAllSoftSkills();
  //     assertEquals(2, softSkills.size());
  // }
}
