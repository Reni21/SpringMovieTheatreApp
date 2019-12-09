package com.theatre.movie.service;

import com.theatre.movie.dto.BookingViewDto;
import com.theatre.movie.entity.*;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class BookingService {
    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class);
    private BookingRepository bookingRepo;
    private UserRepository userRepo;
    private SeatRepository seatRepo;
    private MovieSessionRepository movieSessionRepo;

    public void createBooking(BookedSeatsForm bookedSeatsForm, String username) {
        Integer movieSessionId = Integer.parseInt(bookedSeatsForm.getMovieSessionId());

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

    @Transactional
    public List<BookingViewDto> getActualUsersBookingById(String username) {
        LOG.info("Get actual booking for user username=" + username);
        User user = userRepo.findByUsername(username);
        List<Booking> bookings = bookingRepo.getAllActualBookingByUserId(user.getId());
        LOG.info("Extracted bookings:\n{}", bookings);
        List<BookingViewDto> dtos = new ArrayList<>();
        bookings.forEach(booking -> {
            BookingViewDto dto = mapBookingViewDto(booking);
            dtos.add(dto);

        });
        return dtos;
    }

    private BookingViewDto mapBookingViewDto(Booking booking){
        MovieSession movieSession = booking.getMovieSession();
        Movie movie = booking.getMovieSession().getMovie();
        Seat seat = booking.getBookedSeat();
        return new BookingViewDto(
                booking.getBookingId(),
                movie.getTitle(),
                movie.getDurationMinutes(),
                movieSession.getStartAt(),
                movieSession.getHall().getHallName(),
                seat.getRow(), seat.getPlace());
    }
}
