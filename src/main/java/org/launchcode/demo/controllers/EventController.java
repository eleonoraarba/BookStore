package org.launchcode.demo.controllers;



import org.launchcode.demo.data.EventData;
import org.launchcode.demo.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {


    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    // at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(  new Event());
        return "events/create";
    }

    // at /events/create
    @PostMapping("create")
    public String processcreateEvent(@ModelAttribute @Valid Event newEvent, Errors error, Model model){
        if(error.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
       EventData.add(newEvent);
       return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam (required = false) int[] eventIds ){
        if(eventIds != null){
            for(int id: eventIds){
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }
}
