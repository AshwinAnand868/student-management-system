package com.college.studentmanagement.service.impl;

import com.college.studentmanagement.entity.Student;
import com.college.studentmanagement.exception.ResourceNotFoundException;
import com.college.studentmanagement.repository.StudentRepository;
import com.college.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setCourse(studentDetails.getCourse());
        existingStudent.setAge(studentDetails.getAge());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        studentRepository.delete(existingStudent);
    }

    @Override
    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourseIgnoreCase(course);
    }
}
