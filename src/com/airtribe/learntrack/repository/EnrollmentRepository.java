package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.ArrayList;

public class EnrollmentRepository {

    private static final ArrayList<Enrollment> enrollments = new ArrayList<>();

    // Add an enrollment
    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    // Find enrollment by ID
    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        return null;
    }

    // Get all enrollments
    public ArrayList<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    // Update an enrollment
    public boolean update(Enrollment updatedEnrollment) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getId() == updatedEnrollment.getId()) {
                enrollments.set(i, updatedEnrollment);
                return true;
            }
        }
        return false;
    }


    // Find enrollments by student ID
    public ArrayList<Enrollment> findByStudentId(int studentId) {
        ArrayList<Enrollment> found = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                found.add(enrollment);
            }
        }
        return found;
    }


    // Check if student is already enrolled in course
    public boolean existsEnrollment(int studentId, int courseId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId && enrollment.getCourseId() == courseId) {
                return true;
            }
        }
        return false;
    }
}