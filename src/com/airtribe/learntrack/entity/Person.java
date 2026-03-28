package com.airtribe.learntrack.entity;

public class Person {
    public int id;
    public String firstName, lastName, email;

    public Person (int id,
                   String firstname,
                   String lastName,
                   String email) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFullName() {
        return firstName+ " " + lastName;
    }

    @Override
    public String toString() {
        return "Person [id=" + id +
                ", name=" + getFullName() +
                ", email=" + email + "]";
    }


}
