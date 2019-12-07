package com.theatre.movie.service;

import com.theatre.movie.entity.Booking;
import com.theatre.movie.entity.Seat;
import com.theatre.movie.entity.User;
import com.theatre.movie.form.BookedSeatsForm;
import com.theatre.movie.form.dto.CreateBookingDto;
import com.theatre.movie.repository.BookingRepository;
import com.theatre.movie.repository.MovieSessionRepository;
import com.theatre.movie.repository.SeatRepository;
import com.theatre.movie.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class BookingService {
    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class);
    private BookingRepository bookingRepo;
    private UserRepository userRepo;
    private SeatRepository seatRepo;
    private MovieSessionRepository movieSessionRepo;

    public void createBooking(BookedSeatsForm bookedSeatsForm, Integer movieSessionId, String username) {

        for (String seatId : bookedSeatsForm.getBookedSeats()) {
            Booking booking = new Booking(
                    LocalDateTime.now(),
                    userRepo.findByUsername(username),
                    seatRepo.findById(Integer.parseInt(seatId)).get(),
                    movieSessionRepo.findById(movieSessionId).get()
            );
            Booking createdBooking = bookingRepo.save(booking); // todo: exception if something went wrong
            LOG.info("Created new booking: \n{}", createdBooking);
        }
    }

//    public List<BookingViewDto> getActualUsersBookingById(int userId) {
//        LOG.info("Get actual booking for user id=" + userId);
//        return bookingRepo.getAllActualBookingByUserId(userId);
//    }
}
