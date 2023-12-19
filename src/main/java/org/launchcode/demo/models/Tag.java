package org.launchcode.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {
    @NotBlank(message = "Name is required")
    @jakarta.validation.constraints.Size(min=1, max=25)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }
    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return "#" + name + " ";
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
