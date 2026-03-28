package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.time.LocalDate;

public class Enrollment {
    private int id, studentId, courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    // Parameterized constructor
    public Enrollment(int id,
                      int studentId,
                      int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now();
        this.status = EnrollmentStatus.PENDING;
    }

    // Complete constructor - overlaoding
    public Enrollment(int id,
                      int studentId,
                      int courseId,
                      LocalDate enrollmentDate,
                      EnrollmentStatus status) {
        this(id, studentId, courseId);
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }


    // Getters
    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment [id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", date=" + enrollmentDate +
                ", status=" + status + "]";
    }



}
