-- liquibase formatted sql

-- changeset Nhan Nguyen:MECP-2-2
INSERT INTO users (id, email, password, role, created_on, modified_on)
VALUES (-1, 'admin@local.dev', '$2a$10$ydCe2yychqC5yusG14KAaeyALVi2V0eWyyZwmyKxkG6N5tKRoIqqi', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
