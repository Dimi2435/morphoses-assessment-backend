package com.morphoses.assessment.dto;

import java.util.Map;
import java.util.UUID;

public class AnswerSubmissionRequest {
    // Map: SoftSkill ID -> Answer (1-5)
    private Map<UUID, Integer> answers;

    public Map<UUID, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<UUID, Integer> answers) {
        this.answers = answers;
    }
}