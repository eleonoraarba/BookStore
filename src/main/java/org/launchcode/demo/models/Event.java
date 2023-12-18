package org.launchcode.demo.models;

import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Event {
    private int id;
    private static int nextId=1;

    @NotBlank(message = "Name is required")
    @jakarta.validation.constraints.Size(min=3, max=50, message = "Name must be between 3 and 50 characters!")
    private String name;

    @jakarta.validation.constraints.Size(max=500, message = "Description too long!")
    private String description;


    @NotBlank(message = "Email is required")
    @jakarta.validation.constraints.Email(message = "Invalid emai. Try again!")
    private String contactEmail;
    public Event(String name, String description, String contactEmail) {
        this.name = name;
        this.description=description;
        this.contactEmail=contactEmail;
        this.id=nextId;
        nextId++;
    }

    public Event() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Email(message = "Invalid email. Try again!")
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}