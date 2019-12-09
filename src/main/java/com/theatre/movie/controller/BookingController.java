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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {
    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);
    private BookingService bookingService;

    @GetMapping("/tickets")
    public String getTicketsPage(Authentication authentication, Model model) {
        model.addAttribute("activeTab", "tickets");
        List<BookingViewDto> bookings = bookingService.getActualUsersBookingById(authentication.getName());
        LOG.info("Extracted bookings:\n" + bookings);
        model.addAttribute("bookings", bookings);
        return "user-tickets";
    }

    @PostMapping("/booking")
    public String postSelectedSeats(@ModelAttribute BookedSeatsForm bookedSeatsForm,
                                    Authentication authentication) {
        String username =  authentication.getName();
        LOG.info("Booking for username: {}.\nForm data: {}", username, bookedSeatsForm);
        bookingService.createBooking(bookedSeatsForm, username);
        return "redirect:/tickets";
    }
}
