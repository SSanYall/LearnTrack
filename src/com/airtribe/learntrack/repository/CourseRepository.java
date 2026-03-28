package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;

public class CourseRepository {
    private static ArrayList<Course> courses = new ArrayList<>();

    // Add a course
    public void save(Course course) {
        courses.add(course);
    }

    // Find course by ID
    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    // Get all courses
    public ArrayList<Course> findAll() {
        return new ArrayList<>(courses);
    }

    // Update a course
    public boolean update(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == updatedCourse.getId()) {
                courses.set(i, updatedCourse);
                return true;
            }
        }
        return false;
    }

    // Delete by ID
    public boolean delete(int id) {
        return courses.removeIf(course -> course.getId() == id);
    }

}
