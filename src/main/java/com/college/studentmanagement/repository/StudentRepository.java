package com.college.studentmanagement.repository;

import com.college.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Retrieve students enrolled in a specific course (case-insensitive).
     *
     * @param course Course name
     * @return List of matching students
     */
    List<Student> findByCourseIgnoreCase(String course);

    /**
     * Check if a student with the given email already exists.
     *
     * @param email Email address
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);
}
