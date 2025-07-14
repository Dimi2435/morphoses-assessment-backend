package com.morphoses.assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import java.util.UUID;

/**
 * Request object for submitting answers. Contains a map of SoftSkill IDs to their corresponding
 * answers.
 *
 * <p>Author: Dimitrios Milios
 */
@Schema(description = "Request object for submitting answers")
public class AnswerSubmissionRequest {
  // Map: SoftSkill ID -> Answer (1-5)
  @Schema(description = "A map of SoftSkill IDs to their corresponding answers")
  private Map<UUID, Integer> answers;

  public Map<UUID, Integer> getAnswers() {
    return answers;
  }

  public void setAnswers(Map<UUID, Integer> answers) {
    this.answers = answers;
  }
}
