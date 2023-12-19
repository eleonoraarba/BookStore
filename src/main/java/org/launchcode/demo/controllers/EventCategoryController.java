package org.launchcode.demo.controllers;

import jakarta.validation.Valid;
import org.launchcode.demo.data.EventCategoryRepository;
import org.launchcode.demo.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllCategory(Model model) {
        model.addAttribute("title", "All Event Categories");
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String displayCreateCategoryForm(Model model) {
        model.addAttribute("title", "Create EventCategory");
        model.addAttribute(new EventCategory());

        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateCategoryForm(@ModelAttribute @Valid EventCategory newCategory,
                                            Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create EventCategory");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newCategory);
        return "redirect:/eventCategories";
    }

}