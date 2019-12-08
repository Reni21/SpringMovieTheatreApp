package com.theatre.movie.controller;

import com.theatre.movie.dto.BookingViewDto;
import com.theatre.movie.form.BookedSeatsForm;
import com.theatre.movie.service.BookingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping(value = "/booking")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {
    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);
    private BookingService bookingService;

    @GetMapping("/tickets")
    public String getTicketsPage(Authentication authentication, Model model) {
        model.addAttribute("activeTab", "tickets");
        List<BookingViewDto> bookings = bookingService.getActualUsersBookingById(authentication.getName());
        LOG.info("Extracted bookings:\n" + bookings);
        model.addAttribute("bookings" , bookings);
        return "user-tickets";
    }

    @PostMapping("/booking/{movieSessionId}")
    public String postSelectedSeats(@PathVariable String movieSessionId,
                                    @ModelAttribute BookedSeatsForm bookedSeatsForm,
                                    Authentication authentication) {
        LOG.info("Book seat for movie session id={}", movieSessionId);
        LOG.info("Form: {}", bookedSeatsForm);
        LOG.info("Authentication: {}", authentication);
        bookingService.createBooking(bookedSeatsForm, Integer.parseInt(movieSessionId), authentication.getName());
        return "redirect:/tickets";
    }
}
