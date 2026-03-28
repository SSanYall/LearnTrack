package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.Objects;

public class StudentRepository {
    private static final ArrayList<Student> students = new ArrayList<>();

    // Add student
    public void save(Student student) {
        students.add(student);
    }

    public Student findById(int id) {
        for(Student student : students) {
            if(student.id == id) return student;
        }
        return null;
    }

    // Get all students
    public ArrayList<Student> findAll() {
        return new ArrayList<>(students);
    }

    // Update a student
    public boolean update(Student updatedStudent) {
        for(int i=0; i<students.size(); i++) {
            if(students.get(i).id == updatedStudent.id) {
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false;
    }

    // Delete a student
    public boolean delete(int id) {
        return students.removeIf(student -> student.id == id);
    }

    // find by email
    public Student findByEmail(String email) {
        for(Student student : students) {
            if(student.email != null && Objects.equals(student.email, email)) return student;
        }
        return null;
    }

}
