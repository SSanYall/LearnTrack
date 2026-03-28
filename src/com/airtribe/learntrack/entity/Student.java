package com.airtribe.learntrack.entity;

public class Student extends Person{
    private int batch;
    private boolean active;

    // Creating a parameterized constructor
    public Student(int id,
                   String firstName,
                   String lastname) {
        super(id, firstName, lastname, null);
        this.active = true;

    }

    // Overloaded the Constructor
    public Student(int id,
                   String firstName,
                   String lastname,
                   String email) {
        super(id, firstName, lastname, email);
    }

    // Complete constructor - Overloading
    public Student(int id,
                   String firstName,
                   String lastname,
                   String email,
                   int batch,
                   boolean active) {
        super(id, firstName, lastname, email);
        this.batch = batch;
        this.active = active;
    }

    public int getBatch() {
        return batch;
    }

    public boolean getActive() {
        return active;
    }

    public boolean setActive(boolean active) {
        this.active = active;
        return this.active;
    }

    @Override
    public String toString() {
        return "Student [id=" + id +
                ", name=" + getFullName() +
                ", email=" + email +
                ", batch=" + batch +
                ", active=" + active + "]";
    }
}
