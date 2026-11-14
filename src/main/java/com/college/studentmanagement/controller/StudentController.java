package com.college.studentmanagement.controller;

import com.college.studentmanagement.entity.Student;
import com.college.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for managing Student resources.
 * Base URL: /api/students
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*") // Allows cross-origin requests for testing and frontend integration
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 1. Add a new student
     * POST /api/students
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    /**
     * 2. Retrieve all students
     * GET /api/students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * 3. Retrieve student by ID
     * GET /api/students/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /**
     * 4. Update student details
     * PUT /api/students/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable("id") Long id,
            @Valid @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    /**
     * 5. Delete a student
     * DELETE /api/students/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Student deleted successfully with id: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 6. Retrieve students by course
     * GET /api/students/course/{course}
     */
    @GetMapping("/course/{course}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable("course") String course) {
        List<Student> students = studentService.getStudentsByCourse(course);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
