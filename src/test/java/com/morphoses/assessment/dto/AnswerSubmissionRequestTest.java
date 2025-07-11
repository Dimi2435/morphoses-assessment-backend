package com.morphoses.assessment.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class AnswerSubmissionRequestTest {

  @Test
  void testGettersAndSetters() {
    AnswerSubmissionRequest request = new AnswerSubmissionRequest();
    Map<UUID, Integer> answers = new HashMap<>();
    UUID skillId = UUID.randomUUID();
    answers.put(skillId, 4);

    request.setAnswers(answers);

    assertEquals(answers, request.getAnswers());
  }
}
