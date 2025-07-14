-- V2__insert_initial_data.sql

-- Soft Skills (Predefined list of 36 - adding a few examples)
INSERT INTO soft_skills (id, name) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Teamwork'),
    ('22222222-2222-2222-2222-222222222222', 'Communication'),
    ('33333333-3333-3333-3333-333333333333', 'Problem-solving'),
    ('44444444-4444-4444-4444-444444444444', 'Critical Thinking'),
    ('55555555-5555-5555-5555-555555555555', 'Creativity'),
    ('66666666-6666-6666-6666-666666666666', 'Adaptability');
    -- Add more soft skills up to 36 as needed

-- Users
INSERT INTO users (id, user_type, name) VALUES
    ('a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0', 'INSTRUCTOR', 'Alice Instructor'),
    ('b1b1b1b1-b1b1-b1b1-b1b1-b1b1b1b1b1b1', 'INSTRUCTOR', 'Bob Instructor'),
    ('c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', 'KID', 'Charlie Kid'),
    ('d3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3', 'KID', 'Dana Kid'),
    ('e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4', 'KID', 'Eve Kid');

-- Classrooms
INSERT INTO classrooms (id, name) VALUES
    ('f5f5f5f5-f5f5-f5f5-f5f5-f5f5f5f5f5f5', 'Math Heroes'),
    ('g6g6g6g6-g6g6-g6g6-g6g6-g6g6g6g6g6g6', 'Code Wizards');

-- Sessions
-- Session 1: Not completed, for question retrieval
INSERT INTO sessions (id, classroom_id, start_time, end_time, is_completed) VALUES
    ('s0000000-0000-0000-0000-000000000001', 'f5f5f5f5-f5f5-f5f5-f5f5-f5f5f5f5f5f5', '2025-07-05 08:00:00', '2025-07-05 09:00:00', FALSE);

-- Session 2: Completed, for answer submission/retrieval
INSERT INTO sessions (id, classroom_id, start_time, end_time, is_completed) VALUES
    ('s0000000-0000-0000-0000-000000000002', 'f5f5f5f5-f5f5-f5f5-f5f5-f5f5f5f5f5f5', '2025-07-06 10:00:00', '2025-07-06 11:00:00', TRUE);

-- Session 3: Not completed, for question retrieval
INSERT INTO sessions (id, classroom_id, start_time, end_time, is_completed) VALUES
    ('s0000000-0000-0000-0000-000000000003', 'g6g6g6g6-g6g6-g6g6-g6g6-g6g6g6g6g6g6', '2025-07-07 14:00:00', '2025-07-07 15:00:00', FALSE);

-- Session Soft Skills
INSERT INTO session_soft_skills (session_id, soft_skill_id) VALUES
    ('s0000000-0000-0000-0000-000000000001', '11111111-1111-1111-1111-111111111111'), -- Session 1: Teamwork
    ('s0000000-0000-0000-0000-000000000001', '22222222-2222-2222-2222-222222222222'), -- Session 1: Communication
    ('s0000000-0000-0000-0000-000000000002', '33333333-3333-3333-3333-333333333333'), -- Session 2: Problem-solving
    ('s0000000-0000-0000-0000-000000000002', '44444444-4444-4444-4444-444444444444'), -- Session 2: Critical Thinking
    ('s0000000-0000-0000-0000-000000000003', '55555555-5555-5555-5555-555555555555'), -- Session 3: Creativity
    ('s0000000-0000-0000-0000-000000000003', '66666666-6666-6666-6666-666666666666'); -- Session 3: Adaptability

-- Session Participants
INSERT INTO session_participants (session_id, user_id, is_absent) VALUES
    ('s0000000-0000-0000-0000-000000000001', 'a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0', FALSE), -- Session 1: Alice Instructor
    ('s0000000-0000-0000-0000-000000000001', 'c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', FALSE), -- Session 1: Charlie Kid
    ('s0000000-0000-0000-0000-000000000001', 'd3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3', TRUE),  -- Session 1: Dana Kid (Absent)
    ('s0000000-0000-0000-0000-000000000002', 'a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0', FALSE), -- Session 2: Alice Instructor
    ('s0000000-0000-0000-0000-000000000002', 'c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', FALSE), -- Session 2: Charlie Kid
    ('s0000000-0000-0000-0000-000000000002', 'e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4', FALSE), -- Session 2: Eve Kid
    ('s0000000-0000-0000-0000-000000000003', 'b1b1b1b1-b1b1-b1b1-b1b1-b1b1b1b1b1b1', FALSE), -- Session 3: Bob Instructor
    ('s0000000-0000-0000-0000-000000000003', 'd3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3', FALSE), -- Session 3: Dana Kid
    ('s0000000-0000-0000-0000-000000000003', 'e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4', FALSE); -- Session 3: Eve Kid

-- Instructor Answers for Session 2 (Completed Session)
INSERT INTO instructor_answers (id, session_id, instructor_id, kid_id, soft_skill_id, answer) VALUES
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0', 'c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', '33333333-3333-3333-3333-333333333333', 4), -- Charlie, Problem-solving
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0', 'c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', '44444444-4444-4444-4444-444444444444', 3), -- Charlie, Critical Thinking
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0', 'e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4', '33333333-3333-3333-3333-333333333333', 5), -- Eve, Problem-solving
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'a0a0a0a0-a0a0-a0a0-a0a0-a0a0a0a0a0a0', 'e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4', '44444444-4444-4444-4444-444444444444', 4); -- Eve, Critical Thinking

-- Kid Answers for Session 2 (Completed Session)
INSERT INTO kid_answers (id, session_id, kid_id, soft_skill_id, answer) VALUES
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', '33333333-3333-3333-3333-333333333333', 5), -- Charlie, Problem-solving
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'c2c2c2c2-c2c2-c2c2-c2c2-c2c2c2c2c2c2', '44444444-4444-4444-4444-444444444444', 3), -- Charlie, Critical Thinking
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4', '33333333-3333-3333-3333-333333333333', 4), -- Eve, Problem-solving
    (gen_random_uuid(), 's0000000-0000-0000-0000-000000000002', 'e4e4e4e4-e4e4-e4e4-e4e4-e4e4e4e4e4e4', '44444444-4444-4444-4444-444444444444', 5); -- Eve, Critical Thinking