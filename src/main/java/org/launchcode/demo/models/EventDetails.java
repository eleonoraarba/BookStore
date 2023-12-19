package org.launchcode.demo.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class EventDetails extends AbstractEntity{

    @jakarta.validation.constraints.Size(max=500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required")
    @jakarta.validation.constraints.Email(message = "Invalid email. Try again!")
    private String contactEmail;

    public EventDetails(String description, String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;

    }

    public EventDetails(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
