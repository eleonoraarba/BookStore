package org.launchcode.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
//    @GetMapping("hello")
//    @ResponseBody
//    public String hello(){
//        return "Hello, Spring!";
//    }

    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye, Spring!";
    }

    //handles requests /hello?name=LaunchCode
   @RequestMapping( value ="hello", method = {RequestMethod.GET, RequestMethod.POST } )
    public String helloWithQueryPara(@RequestParam String name, Model model ){
        String greeting= "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    // request /hello/LaunchCode
    @GetMapping("hello/{name}")
    public String helloWithPathPara(@PathVariable String name, Model model) {
        String greeting= "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    @GetMapping("form")
    public String helloForm(){
        return "form";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model){
        List<String> names= new ArrayList<>();
        names.add("LaunchCode");
        names.add("Java");
        names.add("JavaScript");
        model.addAttribute("names", names);
        return "hello-list";
    }
}
