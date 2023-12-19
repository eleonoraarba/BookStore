package org.launchcode.demo.controllers;



import jakarta.persistence.criteria.CriteriaBuilder;
import org.launchcode.demo.data.EventCategoryRepository;
import org.launchcode.demo.data.EventData;
import org.launchcode.demo.data.EventRespository;
import org.launchcode.demo.data.TagRepository;
import org.launchcode.demo.models.Event;
import org.launchcode.demo.models.EventCategory;
import org.launchcode.demo.models.EventType;
import org.launchcode.demo.models.Tag;
import org.launchcode.demo.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRespository eventRespository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId==null){
            model.addAttribute("title", "All Events");
            model.addAttribute("events",eventRespository.findAll());
        }
        else{
           Optional<EventCategory> result= eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title", "Invalid Category ID"+ categoryId);
            }
            else {
                EventCategory category=result.get();
                model.addAttribute("title","Events in category:" + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }

        return "events/index";
    }

    // at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(  new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    // at /events/create
    @PostMapping("create")
    public String processcreateEvent(@ModelAttribute @Valid Event newEvent, Errors error, Model model){
        if(error.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
       eventRespository.save(newEvent);
       return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRespository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam (required = false) int[] eventIds ){
        if(eventIds != null){
            for(int id: eventIds){
               eventRespository.deleteById(id);
            }
        }
        return "redirect:/events";
    }

    @GetMapping("detail")
    public String displayEventDetail(@RequestParam Integer eventId, Model model){
        Optional<Event> result= eventRespository.findById(eventId);

        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Event id" + eventId);
        }
        else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }
        return "events/detail";
    }

    @GetMapping("add-tag")
    public String displayAddTagFrom(@RequestParam Integer eventId, Model model) {
        Optional<Event> result= eventRespository.findById(eventId);
        Event event = result.get();

        model.addAttribute("title", "Add tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";

    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Errors errors, Model model){
        if(!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRespository.save(event);
            }
            return "redirect:/events/detail?eventId=" + event.getId();
        }
        // Handle errors here if needed
        return "redirect:/events";
    }
}
