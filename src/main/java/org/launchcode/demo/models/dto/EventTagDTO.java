package org.launchcode.demo.models.dto;

import org.launchcode.demo.models.Event;
import org.launchcode.demo.models.Tag;

import javax.swing.plaf.PanelUI;
import javax.validation.constraints.NotNull;
import java.util.PrimitiveIterator;

public class EventTagDTO {
    @NotNull
    private Event event;

    @NotNull
   private Tag tag;

    public EventTagDTO(){};

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
