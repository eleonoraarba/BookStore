package org.launchcode.demo.models;

public enum EventType {

    CONFeRENCE("Conference"),
    MEETUP("Meetup"),
    WORKSHOP("WorkShop"),
    SOCIAL("Social");


    private final String displayname;

    EventType(String displayname) {
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }
}
