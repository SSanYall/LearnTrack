package com.airtribe.learntrack.entity;

public class Trainer extends Person{
    private String specialization;
    private boolean active;

    // Parameterized constructor
    public Trainer(int id,
                   String firstname,
                   String lastName,
                   String email) {
        super(id, firstname, lastName, email);
        this.active = true;
    }

    // Complete constructor - Overloading
    public Trainer(int id,
                   String firstname,
                   String lastName,
                   String email,
                   String specialization,
                   boolean active) {
        super(id, firstname, lastName, email);
        this.specialization = specialization;
        this.active = active;
    }

    // To
    @Override
    public String toString() {
        return "Trainer [id=" + id +
                ", name=" + getFullName() +
                ", specialization=" + specialization +
                ", active=" + active + "]";
    }
}
