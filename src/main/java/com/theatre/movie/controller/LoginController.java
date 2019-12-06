package com.theatre.movie.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/login")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SessionAttributes("user")
public class LoginController {

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("activeTab", "account");
        return "login";
    }
}
