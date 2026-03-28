# LearnTrack - Learning Management System

A console-based Learning Management System (LMS) built in Java for managing students, courses, and enrollments.

## Features

- **Student Management**: Add, view, search, activate/deactivate students
- **Course Management**: Add, view, activate/deactivate courses
- **Enrollment Management**: Enroll students, complete/cancel enrollments
- **In-Memory Storage**: All data stored in ArrayLists (no database required)

## Project Structure

```
src/com/airtribe/learntrack/
├── entity/          # Domain models (Student, Course, Enrollment, Person, Trainer)
├── enums/           # Status enums (CourseStatus, EnrollmentStatus)
├── exception/       # Custom exceptions (EntityNotFoundException, InvalidInputException)
├── repository/      # Data access layer (in-memory storage)
├── service/         # Business logic layer
├── util/            # Utilities (IdGenerator, InputValidator)
├── constants/       # App constants and menu options
└── ui/              # Main.java (console UI entry point)
```

## Architecture

### Layered Architecture Flow

```mermaid
flowchart TB
    subgraph UI[Presentation Layer]
        Main[Main.java<br/>Console Menu]
    end

    subgraph Service[Service Layer]
        SS[StudentService]
        CS[CourseService]
        ES[EnrollmentService]
    end

    subgraph Repository[Repository Layer]
        SR[StudentRepository<br/>ArrayList&lt;Student&gt;]
        CR[CourseRepository<br/>ArrayList&lt;Course&gt;]
        ER[EnrollmentRepository<br/>ArrayList&lt;Enrollment&gt;]
    end

    subgraph Entity[Entity Layer]
        ST[Student]
        CO[Course]
        EN[Enrollment]
        PE[Person]
    end

    Main --> SS
    Main --> CS
    Main --> ES

    SS --> SR
    CS --> CR
    ES --> SR
    ES --> CR
    ES --> ER

    SR --> ST
    CR --> CO
    ER --> EN
    ST --> PE
```

### Class Hierarchy

```mermaid
classDiagram
    class Person {
        +int id
        +String firstName
        +String lastName
        +String email
        +getFullName()
    }

    class Student {
        -int batch
        -boolean active
        +getBatch()
        +getActive()
        +setActive(boolean)
    }

    class Trainer {
        -String specialization
        -boolean active
    }

    class Course {
        -int id
        -String courseName
        -String description
        -int durationInWeeks
        -CourseStatus status
        +getId()
        +getCourseName()
        +getStatus()
        +setStatus(CourseStatus)
    }

    class Enrollment {
        -int id
        -int studentId
        -int courseId
        -LocalDate enrollmentDate
        -EnrollmentStatus status
        +getId()
        +getStudentId()
        +getCourseId()
        +getStatus()
        +setStatus(EnrollmentStatus)
    }

    Person <|-- Student
    Person <|-- Trainer
```

### Enrollment Flow

```mermaid
sequenceDiagram
    actor User
    participant Main
    participant ES as EnrollmentService
    participant SS as StudentService
    participant CS as CourseService
    participant ER as EnrollmentRepository

    User->>Main: Enroll Student
    Main->>ES: enrollStudent(studentId, courseId)

    ES->>SS: findById(studentId)
    alt Student Not Found
        SS-->>ES: null
        ES-->>Main: EntityNotFoundException
    else Student Inactive
        SS-->>ES: Student (inactive)
        ES-->>Main: InvalidInputException
    else Student Found
        SS-->>ES: Student (active)
    end

    ES->>CS: findById(courseId)
    alt Course Not Found
        CS-->>ES: null
        ES-->>Main: EntityNotFoundException
    else Course Inactive
        CS-->>ES: Course (inactive)
        ES-->>Main: InvalidInputException
    else Course Found
        CS-->>ES: Course (active)
    end

    ES->>ER: existsEnrollment(studentId, courseId)
    alt Already Enrolled
        ER-->>ES: true
        ES-->>Main: InvalidInputException
    else Not Enrolled
        ER-->>ES: false
        ES->>ER: save(new Enrollment(...))
        ER-->>ES: Saved
        ES-->>Main: Enrollment Created
        Main-->>User: Success Message
    end
```

## Menu Options

### Main Menu
```
1. Student Management
2. Course Management
3. Enrollment Management
Exit to quit
```

### Student Management
```
1. Add Student
2. View All Students
3. Search by ID
4. Deactivate Student
5. Activate Student
0. Back to Main Menu
```

### Course Management
```
1. Add Course
2. View All Courses
3. Activate Course
4. Deactivate Course
0. Back to Main Menu
```

### Enrollment Management
```
1. Enroll Student
2. View Student Enrollments
3. Complete Enrollment
4. Cancel Enrollment
0. Back to Main Menu
```


## Validations

| Feature | Validation |
|---------|------------|
| Student | First/Last name required, email format, batch must be number |
| Course | Name required, duration > 0 weeks |
| Enrollment | Student must be active, Course must be active, No duplicate enrollments |
| IDs | Must be positive numbers |

## Status Enums

### CourseStatus
- `ACTIVE` - Course accepting enrollments
- `INACTIVE` - Course not accepting enrollments
- `DRAFT` - Reserved for future use

### EnrollmentStatus
- `PENDING` - Enrollment created, awaiting completion
- `APPROVED` - Reserved for future use
- `REJECTED` - Enrollment cancelled
- `COMPLETED` - Course completed by student

## Tech Stack

- **Language**: Java (JDK 17+)
- **Storage**: In-Memory (ArrayList)
- **Architecture**: Layered Architecture (Presentation → Service → Repository → Entity)
- **Design Patterns**: Inheritance (Person hierarchy)


