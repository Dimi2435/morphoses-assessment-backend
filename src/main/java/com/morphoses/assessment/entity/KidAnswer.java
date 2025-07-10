package com.morphoses.assessment.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "kid_answers")
public class KidAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne
    @JoinColumn(name = "kid_id", nullable = false)
    private User kid; // UserType KID

    @ManyToOne
    @JoinColumn(name = "soft_skill_id", nullable = false)
    private SoftSkill softSkill;

    @Column(nullable = false)
    private Integer answer; // Scale of 1-5 [cite: 15]

    public KidAnswer() {
    }

    public KidAnswer(Session session, User kid, SoftSkill softSkill, Integer answer) {
        this.session = session;
        this.kid = kid;
        this.softSkill = softSkill;
        this.answer = answer;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getKid() {
        return kid;
    }

    public void setKid(User kid) {
        this.kid = kid;
    }

    public SoftSkill getSoftSkill() {
        return softSkill;
    }

    public void setSoftSkill(SoftSkill softSkill) {
        this.softSkill = softSkill;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        if (answer < 1 || answer > 5) {
            throw new IllegalArgumentException("Answer must be between 1 and 5.");
        }
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KidAnswer kidAnswer = (KidAnswer) o;
        return Objects.equals(id, kidAnswer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}