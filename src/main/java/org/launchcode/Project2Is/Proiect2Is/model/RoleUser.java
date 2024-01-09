package org.launchcode.Project2Is.Proiect2Is.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RoleUser {
    @Id
    private Long id;
    private String role; // Use the Roles enum directly


    public RoleUser(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleUser{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
