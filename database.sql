-- =======================================================
-- Student Management System Database Setup Script
-- Database: MySQL
-- =======================================================

-- 1. Create database if it does not already exist
CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;

-- 2. Create students table (Note: Spring Data JPA will also create/update this automatically)
CREATE TABLE IF NOT EXISTS students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    course VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

-- 3. Optional sample test data
INSERT INTO students (name, email, course, age) VALUES
('John Doe', 'john.doe@example.com', 'Computer Science', 21),
('Jane Smith', 'jane.smith@example.com', 'Information Technology', 22),
('Alex Johnson', 'alex.j@example.com', 'Computer Science', 20),
('Emily Brown', 'emily.b@example.com', 'Electronics', 23)
ON DUPLICATE KEY UPDATE name=name;

-- 4. Verify data
SELECT * FROM students;
