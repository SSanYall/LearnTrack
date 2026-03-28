package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);


    // Service instances
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();

    public void main() {

        mainMenu();

    }


    private void mainMenu() {
        while(true) {
            displayMenu();
            String choice = readString("Enter an option: ");

            switch (choice) {
                case MenuOptions.STUDENT_MANAGEMENT -> studentSubMenu();
                case MenuOptions.COURSE_MANAGEMENT -> courseSubMenu();
                case MenuOptions.ENROLLMENT_MANAGEMENT -> enrollmentSubMenu();
                case MenuOptions.MAIN_EXIT -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Please enter a valid option!");
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().toLowerCase();
    }

    private void displayMenu() {
        System.out.println("\n=== Welcome to LearnTrack ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("Exit to quit");
    }


    //--------------------------------------------------------- Student Sub Menu ---------------------------------------
    private void studentSubMenu() {
        while(true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by ID");
            System.out.println("4. Deactivate Student");
            System.out.println("5. Activate Student");
            System.out.println("0. Back to Main Menu");
            var choice = readString("Enter a choice: ");

            switch (choice) {
                case MenuOptions.STUDENT_ADD -> addStudent();
                case MenuOptions.STUDENT_VIEW_ALL -> viewAllStudents();
                case MenuOptions.STUDENT_SEARCH_BY_ID -> searchStudentById();
                case MenuOptions.STUDENT_DEACTIVATE -> deactivateStudent();
                case MenuOptions.STUDENT_ACTIVATE -> activateStudent();
                case MenuOptions.STUDENT_BACK -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }


        }
    }

    private void addStudent() {
        try {
            String firstName = readString("Enter first name: ");
            String lastName = readString("Enter last name: ");
            String email = readString("Enter email (optional): ");
            if (email.isEmpty()) email = null;

            String batchStr = readString("Enter batch year: ");
            int batch = InputValidator.parseInt(batchStr, "Batch");

            Student student = studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student added successfully! ID: " + student.id);

        }
        catch (RuntimeException e) {
        System.out.println("Error: " + e.getMessage());
        }
    }




    private void viewAllStudents() {
        ArrayList<Student> students = studentService.viewAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- All Students ---");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void searchStudentById() {
        try {
            String idStr = readString("Enter student ID: ");
            int id = InputValidator.parseId(idStr, "Student");

            Student student = studentService.searchStudentById(id);
            System.out.println(student);

        }
        catch ( RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deactivateStudent() {
        try {
            String idStr = readString("Enter student ID to deactivate: ");
            int id = InputValidator.parseId(idStr, "Student");

            boolean success = studentService.deactivateStudent(id);

            if (success) {
                System.out.println("Student deactivated successfully!");
            }
            else {
                System.out.println("Student was already inactive.");
            }
        }
        catch ( RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void activateStudent() {
        try {
            String idStr = readString("Enter student ID to activate: ");
            int id = InputValidator.parseId(idStr, "Student");

            boolean success = studentService.activateStudent(id);

            if (success) {
                System.out.println("Student activated successfully!");
            }
            else {
                System.out.println("Student was already active.");
            }

        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // ----------------------------------------------   Course  Sub Menu ----------------------------------------------
    public void courseSubMenu() {
        while(true) {
            System.out.println("\n=== Course Management ===");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Activate Course");
            System.out.println("4. Deactivate course");
            System.out.println("0. Back to Main Menu");
            var choice = readString("Enter a choice: ");

            switch (choice) {
                case MenuOptions.COURSE_ADD -> addCourse();
                case MenuOptions.COURSE_VIEW_ALL -> viewAllCourses();
                case MenuOptions.COURSE_ACITVATE -> activateCourse();
                case MenuOptions.COURSE_DEACITVATE -> deactivateCourse();
                case MenuOptions.COURSE_BACK -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }


        }
    }

    private void addCourse() {
        try {
            String name = readString("Enter course name: ");
            String description = readString("Enter description: ");
            String durationStr = readString("Enter duration (weeks): ");
            int duration = InputValidator.parseInt(durationStr, "Duration");


            Course course = courseService.addCourse(name, description, duration);
            System.out.println("Course added successfully! ID: " + course.getId());

        }
        catch (RuntimeException e) {
        System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllCourses() {
        ArrayList<Course> courses = courseService.viewAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n--- All Courses ---");

        for (Course c : courses) {
            System.out.println(c);
        }
    }

    private void activateCourse() {
        try {
            String idStr = readString("Enter course ID to activate: ");
            int id = InputValidator.parseId(idStr, "Course");

            boolean success = courseService.activateCourse(id);

            if (success) {
                System.out.println("Course activated successfully!");
            }

        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deactivateCourse() {
        try {
            String idStr = readString("Enter course ID to deactivate: ");
            int id = InputValidator.parseId(idStr, "Course");

            boolean success = courseService.deactivateCourse(id);

            if (success) {
                System.out.println("Course deactivated successfully!");
            }

        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    // -----------------------------------------------Enrollment Sub Menu ----------------------------------------------
    public void enrollmentSubMenu() {
        while(true) {
            System.out.println("\n=== Enrollment Management ===");
            System.out.println("1. Enroll Student");
            System.out.println("2. View Students Enrollments");
            System.out.println("3. Complete Enrollment");
            System.out.println("4. Cancel Enrollment");
            System.out.println("0. Back to Main Menu");
            var choice = readString("Enter a choice: ");

            switch (choice) {
                case MenuOptions.ENROLL_STUDENT -> enrollStudent();
                case MenuOptions.VIEW_STUDENT_ENROLLMENTS -> viewStudentEnrollments();
                case MenuOptions.ENROLLMENT_COMPLETED -> completeEnrollment();
                case MenuOptions.ENROLLMENT_CANCELED -> cancelEnrollment();
                case MenuOptions.ENROLLMENT_BACK -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }


        }
    }

    private void enrollStudent() {
        try {
            String studentIdStr = readString("Enter student ID: ");
            int studentId = InputValidator.parseId(studentIdStr, "Student");

            String courseIdStr = readString("Enter course ID: ");
            int courseId = InputValidator.parseId(courseIdStr, "Course");

            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("Enrollment created! ID: " + enrollment.getId());

        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewStudentEnrollments() {
        try {
            String studentIdStr = readString("Enter student ID: ");
            int studentId = InputValidator.parseId(studentIdStr, "Student");

            ArrayList<Enrollment> enrollments = enrollmentService.viewEnrollmentsByStudent(studentId);

            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this student.");
                return;
            }
            System.out.println("\n--- Enrollments ---");

            for (Enrollment e : enrollments) {
                System.out.println(e);
            }

        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void completeEnrollment() {
        try {
            String idStr = readString("Enter enrollment ID to complete: ");
            int id = InputValidator.parseId(idStr, "Enrollment");

            boolean success = enrollmentService.markAsCompleted(id);

            if (success) {
                System.out.println("Enrollment marked as completed!");
            }
            else {
                System.out.println("Enrollment was already completed.");
            }

        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cancelEnrollment() {
        try {
            String idStr = readString("Enter enrollment ID to cancel: ");
            int id = InputValidator.parseId(idStr, "Enrollment");

            boolean success = enrollmentService.markAsCancelled(id);
            if (success) {
                System.out.println("Enrollment cancelled!");
            }
            else {
                System.out.println("Enrollment was already cancelled/rejected.");
            }

        }
        catch ( RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


