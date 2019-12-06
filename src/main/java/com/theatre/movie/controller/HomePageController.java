package com.theatre.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("activeTab", "main");
        return "index";
    }
}
