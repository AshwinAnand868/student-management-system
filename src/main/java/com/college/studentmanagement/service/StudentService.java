package com.college.studentmanagement.service;

import com.college.studentmanagement.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student studentDetails);

    void deleteStudent(Long id);

    List<Student> getStudentsByCourse(String course);
}
