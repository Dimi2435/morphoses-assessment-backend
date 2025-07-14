-- V2__insert_initial_data.sql (Updated for H2 UUID literal insertion)

-- Soft Skills (Predefined list of 36 - adding a few examples)
-- These are fine as they are not affected by the UUID literal issue if soft_skills.id is UUID
INSERT INTO soft_skills (id, name) VALUES
    (CAST('11111111-1111-1111-1111-111111111111' AS UUID), 'Teamwork'),
    (CAST('22222222-2222-2222-2222-222222222222' AS UUID), 'Communication'),
    (CAST('33333333-3333-3333-3333-333333333333' AS UUID), 'Problem-solving'),
    (CAST('44444444-4444-4444-4444-444444444444' AS UUID), 'Critical Thinking'),
    (CAST('55555555-5555-5555-5555-555555555555' AS UUID), 'Creativity'),
    (CAST('66666666-6666-6666-6666-666666666666' AS UUID), 'Adaptability');
    -- Add more soft skills up to 36 as needed

-- Users
INSERT INTO users (id, user_type, name) VALUES
    (CAST('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0' AS UUID), 'INSTRUCTOR', 'Alice Instructor'),
    (CAST('b1b1b1b1-b1b1-b1b1-b1b1-b1b1b1b1b1b1' AS UUID), 'INSTRUCTOR', 'Bob Instructor'),
    (CAST('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2' AS UUID), 'KID', 'Charlie Kid'),
    (CAST('d3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3' AS UUID), 'KID', 'Dana Kid'),
    (CAST('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4' AS UUID), 'KID', 'Eve Kid');

-- Classrooms
INSERT INTO classrooms (id, name) VALUES
    (CAST('9f0f1f2f-3f4f-5f6f-7f8f-9a0b1c2d3e4f' AS UUID), 'Math Heroes'),
    (CAST('8e1e2f3f-4e5e-6e7e-8e9e-0a1b2c3d4e5f' AS UUID), 'Science Wizards');

-- Sessions
-- Session 1: Not completed, for question retrieval
INSERT INTO sessions (id, classroom_id, start_time, end_time, is_completed) VALUES
    (CAST('5b82e7e3-0c4a-4e8c-8d3e-9f0a2d1b3c4d' AS UUID), CAST('9f0f1f2f-3f4f-5f6f-7f8f-9a0b1c2d3e4f' AS UUID), '2025-07-05 08:00:00', '2025-07-05 09:00:00', FALSE);

-- Session 2: Completed, for answer submission/retrieval
INSERT INTO sessions (id, classroom_id, start_time, end_time, is_completed) VALUES
    (CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('9f0f1f2f-3f4f-5f6f-7f8f-9a0b1c2d3e4f' AS UUID), '2025-07-06 10:00:00', '2025-07-06 11:00:00', TRUE);

-- Session 3: Not completed, for question retrieval
INSERT INTO sessions (id, classroom_id, start_time, end_time, is_completed) VALUES
    (CAST('7e6d5c4b-3a2b-1c0d-e9f8-1234567890ab' AS UUID), CAST('8e1e2f3f-4e5e-6e7e-8e9e-0a1b2c3d4e5f' AS UUID), '2025-07-07 14:00:00', '2025-07-07 15:00:00', FALSE);

-- Session Soft Skills
INSERT INTO session_soft_skills (session_id, soft_skill_id) VALUES
    (CAST('5b82e7e3-0c4a-4e8c-8d3e-9f0a2d1b3c4d' AS UUID), CAST('11111111-1111-1111-1111-111111111111' AS UUID)), -- Session 1: Teamwork
    (CAST('5b82e7e3-0c4a-4e8c-8d3e-9f0a2d1b3c4d' AS UUID), CAST('22222222-2222-2222-2222-222222222222' AS UUID)), -- Session 1: Communication
    (CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('33333333-3333-3333-3333-333333333333' AS UUID)), -- Session 2: Problem-solving
    (CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('44444444-4444-4444-4444-444444444444' AS UUID)), -- Session 2: Critical Thinking
    (CAST('7e6d5c4b-3a2b-1c0d-e9f8-1234567890ab' AS UUID), CAST('55555555-5555-5555-5555-555555555555' AS UUID)), -- Session 3: Creativity (Corrected here)
    (CAST('7e6d5c4b-3a2b-1c0d-e9f8-1234567890ab' AS UUID), CAST('66666666-6666-6666-6666-666666666666' AS UUID)); -- Session 3: Adaptability (Corrected here)

-- Session Participants
INSERT INTO session_participants (session_id, user_id, is_absent) VALUES
    (CAST('5b82e7e3-0c4a-4e8c-8d3e-9f0a2d1b3c4d' AS UUID), CAST('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0' AS UUID), FALSE), -- Session 1: Alice Instructor
    (CAST('5b82e7e3-0c4a-4e8c-8d3e-9f0a2d1b3c4d' AS UUID), CAST('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2' AS UUID), FALSE), -- Session 1: Charlie Kid
    (CAST('5b82e7e3-0c4a-4e8c-8d3e-9f0a2d1b3c4d' AS UUID), CAST('d3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3' AS UUID), TRUE),   -- Session 1: Dana Kid (Absent)
    (CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0' AS UUID), FALSE), -- Session 2: Alice Instructor
    (CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2' AS UUID), FALSE), -- Session 2: Charlie Kid
    (CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4' AS UUID), FALSE), -- Session 2: Eve Kid
    (CAST('7e6d5c4b-3a2b-1c0d-e9f8-1234567890ab' AS UUID), CAST('b1b1b1b1-b1b1-b1b1-b1b1-b1b1b1b1b1b1' AS UUID), FALSE), -- Session 3: Bob Instructor (Corrected here)
    (CAST('7e6d5c4b-3a2b-1c0d-e9f8-1234567890ab' AS UUID), CAST('d3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3' AS UUID), FALSE), -- Session 3: Dana Kid (Corrected here)
    (CAST('7e6d5c4b-3a2b-1c0d-e9f8-1234567890ab' AS UUID), CAST('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4' AS UUID), FALSE); -- Session 3: Eve Kid (Corrected here)

-- Instructor Answers for Session 2 (Completed Session)
INSERT INTO instructor_answers (id, session_id, instructor_id, kid_id, soft_skill_id, answer) VALUES
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0' AS UUID), CAST('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2' AS UUID), CAST('33333333-3333-3333-3333-333333333333' AS UUID), 4),
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0' AS UUID), CAST('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2' AS UUID), CAST('44444444-4444-4444-4444-444444444444' AS UUID), 3),
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0' AS UUID), CAST('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4' AS UUID), CAST('33333333-3333-3333-3333-333333333333' AS UUID), 5),
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0' AS UUID), CAST('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4' AS UUID), CAST('44444444-4444-4444-4444-444444444444' AS UUID), 4);

-- Kid Answers for Session 2 (Completed Session)
INSERT INTO kid_answers (id, session_id, kid_id, soft_skill_id, answer) VALUES
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2' AS UUID), CAST('33333333-3333-3333-3333-333333333333' AS UUID), 5),
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2' AS UUID), CAST('44444444-4444-4444-4444-444444444444' AS UUID), 3),
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4' AS UUID), CAST('33333333-3333-3333-3333-333333333333' AS UUID), 4),
    (RANDOM_UUID(), CAST('4a9f2c8d-1b3e-4f5a-8c7b-6d9e0f1a2b3c' AS UUID), CAST('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4' AS UUID), CAST('44444444-4444-4444-4444-444444444444' AS UUID), 5);