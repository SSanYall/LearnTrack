package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;

public class CourseService {


    private final CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepository();
    }

    // Add new course
    public Course addCourse(String courseName, String description, int durationInWeeks) {

        // Validate input
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new InvalidInputException("Course name cannot be empty");
        }

        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration must be greater than 0");
        }

        // Create and save course
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName.trim(), description, durationInWeeks);
        courseRepository.save(course);
        return course;
    }

    // View all courses
    public ArrayList<Course> viewAllCourses() {
        return courseRepository.findAll();
    }


    // Activate a course
    public boolean activateCourse(int id) {

        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with ID: " + id);
        }

        // Check if already active
        if (course.getStatus() == CourseStatus.ACTIVE) {
            // Already active
            return false;
        }

        course.setStatus(CourseStatus.ACTIVE);
        return courseRepository.update(course);
    }

    // Deactivate a course
    public boolean deactivateCourse(int id) {

        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with ID: " + id);
        }

        // Check if already inactive
        if (course.getStatus() == CourseStatus.INACTIVE) {
            // Already inactive
            return false;
        }

        course.setStatus(CourseStatus.INACTIVE);
        return courseRepository.update(course);
    }

}
