package com.theatre.movie.controller;

import com.theatre.movie.entity.Role;
import com.theatre.movie.exception.UserAlreadyExistException;
import com.theatre.movie.form.SignUpForm;
import com.theatre.movie.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/sign-up")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SignUpController {
    private static final Logger LOG = LoggerFactory.getLogger(SignUpController.class);
    private UserService userService;

    @GetMapping
    public String getSignUpPage(Model model) {
        model.addAttribute("activeTab", "account");
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign-up";
    }

    @PostMapping
    public String registerNewUser(@Valid @ModelAttribute SignUpForm signUpForm, BindingResult error, Model model) {
        LOG.info("Post form: {}", signUpForm);
        model.addAttribute("activeTab", "account");
        if (error.hasErrors()) {
            return "sign-up";
        }
        try {
            userService.registerUser(signUpForm, Role.ROLE_USER);
        } catch (UserAlreadyExistException ex) {
            String msg = ex.getMessage();
            error.rejectValue(msg.substring(0, msg.indexOf(" ")),
                    msg.substring(msg.indexOf(" ") + 1));
            return "sign-up";
        }
        return "redirect:login?msg";
    }
}
