package com.theatre.movie.controller;

import com.theatre.movie.entity.User;
import com.theatre.movie.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/account")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {
    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    private UserService userService;

    @GetMapping("{username}")
    public String getAccountPage(@PathVariable String username, Model model) {
        LOG.info("PathVariable username=" + username);

        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with " + username + " is not found");
        }
        model.addAttribute("activeTab", "account");
        return "account";
    }
}
