package com.theatre.movie.controller;

import com.theatre.movie.form.BookedSeatsForm;
import com.theatre.movie.service.BookingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/booking")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {
    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);
    private BookingService bookingService;

    @PostMapping("{movieSessionId}")
    public String postSelectedSeats(@PathVariable String movieSessionId,
                                    @ModelAttribute BookedSeatsForm bookedSeatsForm,
                                    Authentication authentication,
                                    Model model) {
        LOG.info("Book seat for movie session id={}", movieSessionId);
        LOG.info("Form: {}", bookedSeatsForm);
        LOG.info("Authentication: {}", authentication);
        model.addAttribute("activeTab", "account");
        bookingService.createBooking(bookedSeatsForm, Integer.parseInt(movieSessionId), authentication.getName());
        return "redirect:/account/" + authentication.getName();
    }
}
