package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService() {
        this.enrollmentRepository = new EnrollmentRepository();
        this.studentRepository = new StudentRepository();
        this.courseRepository = new CourseRepository();
    }

    // Enroll a student in a course
    public Enrollment enrollStudent(int studentId, int courseId) {

        // Validate student exists and is active
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + studentId);
        }

        if (!student.getActive()) {
            throw new InvalidInputException("Student is inactive and cannot be enrolled");
        }

        // Validate course exists and is active
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }
        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new InvalidInputException("Course is not active and cannot accept enrollments");
        }

        // Check if already enrolled
        if (enrollmentRepository.existsEnrollment(studentId, courseId)) {
            throw new InvalidInputException("Student is already enrolled in this course");
        }

        // Create enrollment
        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    // View enrollments for a student
    public ArrayList<Enrollment> viewEnrollmentsByStudent(int studentId) {
        // Validate student exists
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + studentId);
        }
        return enrollmentRepository.findByStudentId(studentId);
    }

    // Mark enrollment as completed
    public boolean markAsCompleted(int enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId);
        }

        if (enrollment.getStatus() == EnrollmentStatus.COMPLETED) {
            return false; // Already completed
        }

        enrollment.setStatus(EnrollmentStatus.COMPLETED) ;
        return enrollmentRepository.update(enrollment);
    }

    // Mark enrollment as cancelled
    public boolean markAsCancelled(int enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);

        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId);
        }

        if (enrollment.getStatus() == EnrollmentStatus.REJECTED) {
            return false; // Already rejected/cancelled
        }

        enrollment.setStatus(EnrollmentStatus.REJECTED);
        return enrollmentRepository.update(enrollment);
    }

}
