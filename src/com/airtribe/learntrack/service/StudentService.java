package com.airtribe.learntrack.service;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    // Add new student
    public Student addStudent(String firstName, String lastName, String email, int batch) {

        // Validate input
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidInputException("First name cannot be empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidInputException("Last name cannot be empty");
        }

        // Check if email already exists (if provided)
        if (email != null && !email.trim().isEmpty()) {

            Student existing = studentRepository.findByEmail(email);

            if (existing != null) {
                throw new InvalidInputException("Email already exists: " + email);
            }
        }

        // Create and save student
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName.trim(), lastName.trim(), email, batch, true);
        studentRepository.save(student);
        return student;
    }

    // View all students
    public ArrayList<Student> viewAllStudents() {
        return studentRepository.findAll();
    }

    // Search student by ID
    public Student searchStudentById(int id) {
        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }

        return student;
    }

    // Activate student
    public boolean activateStudent(int id) {

        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }

        if (student.getActive()) {
            return false; // Already active
        }

        student.setActive(true);
        return studentRepository.update(student);
    }

    // Deactivate student (set active = false)
    public boolean deactivateStudent(int id) {
        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }

        if (!student.getActive()) {
            // Already inactive
            return false;
        }

        student.setActive(false);
        return studentRepository.update(student);
    }

}
