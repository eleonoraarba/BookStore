package org.launchcode.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
public class EventCategory extends AbstractEntity {
    @jakarta.validation.constraints.Size(min=3, max=50, message = "Name must be between 3 and 50 characters!")
    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private List<Event> events=new ArrayList<>();

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }


}
