package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.CourseStatus;

public class Course {
    private int id;
    private String courseName, description;
    private int durationInWeeks;
    private CourseStatus status;

    // Parameterized constructor
    public Course( int id,
                   String courseName,
                   String description,
                   int durationInWeeks ) {
        this.id = id;
        this.courseName = courseName;
        this.description =  description;
        this.durationInWeeks = durationInWeeks;
        this.status = CourseStatus.ACTIVE;
    }

    // Complete constructor - Overloading
    public Course( int id,
                   String courseName,
                   String description,
                   int durationInWeeks,
                   CourseStatus status) {
        this(id, courseName, description, durationInWeeks);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Course [id=" + id +
                ", name=" + courseName +
                ", duration=" + durationInWeeks +
                " weeks, status=" + status + "]";
    }



}
