-- V1__create_initial_schema.sql (Modified for H2)

-- Table: users (user_type as VARCHAR)
CREATE TABLE users (
    id UUID PRIMARY KEY,
    user_type VARCHAR(255) NOT NULL, -- Changed from ENUM
    name VARCHAR(255) NOT NULL
);

-- Table: classrooms
CREATE TABLE classrooms (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Table: soft_skills
CREATE TABLE soft_skills (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Table: sessions
CREATE TABLE sessions (
    id UUID PRIMARY KEY,
    classroom_id UUID NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_classroom
        FOREIGN KEY(classroom_id)
        REFERENCES classrooms(id)
);

-- Junction Table: session_soft_skills
CREATE TABLE session_soft_skills (
    session_id UUID NOT NULL,
    soft_skill_id UUID NOT NULL,
    PRIMARY KEY (session_id, soft_skill_id),
    CONSTRAINT fk_session_soft_skills_session
        FOREIGN KEY(session_id)
        REFERENCES sessions(id),
    CONSTRAINT fk_session_soft_skills_soft_skill
        FOREIGN KEY(soft_skill_id)
        REFERENCES soft_skills(id)
);

-- Junction Table: session_participants
CREATE TABLE session_participants (
    session_id UUID NOT NULL,
    user_id UUID NOT NULL,
    is_absent BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (session_id, user_id),
    CONSTRAINT fk_session_participants_session
        FOREIGN KEY(session_id)
        REFERENCES sessions(id),
    CONSTRAINT fk_session_participants_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
);

-- Table: instructor_answers
CREATE TABLE instructor_answers (
    id UUID PRIMARY KEY,
    session_id UUID NOT NULL,
    instructor_id UUID NOT NULL,
    kid_id UUID NOT NULL,
    soft_skill_id UUID NOT NULL,
    answer INTEGER NOT NULL,
    CONSTRAINT fk_instructor_answers_session
        FOREIGN KEY(session_id)
        REFERENCES sessions(id),
    CONSTRAINT fk_instructor_answers_instructor
        FOREIGN KEY(instructor_id)
        REFERENCES users(id),
    CONSTRAINT fk_instructor_answers_kid
        FOREIGN KEY(kid_id)
        REFERENCES users(id),
    CONSTRAINT fk_instructor_answers_soft_skill
        FOREIGN KEY(soft_skill_id)
        REFERENCES soft_skills(id),
    CONSTRAINT chk_instructor_answer_range CHECK (answer >= 1 AND answer <= 5)
);

-- Table: kid_answers
CREATE TABLE kid_answers (
    id UUID PRIMARY KEY,
    session_id UUID NOT NULL,
    kid_id UUID NOT NULL,
    soft_skill_id UUID NOT NULL,
    answer INTEGER NOT NULL,
    CONSTRAINT fk_kid_answers_session
        FOREIGN KEY(session_id)
        REFERENCES sessions(id),
    CONSTRAINT fk_kid_answers_kid
        FOREIGN KEY(kid_id)
        REFERENCES users(id),
    CONSTRAINT fk_kid_answers_soft_skill
        FOREIGN KEY(soft_skill_id)
        REFERENCES soft_skills(id),
    CONSTRAINT chk_kid_answer_range CHECK (answer >= 1 AND answer <= 5)
);