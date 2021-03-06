package com.theatre.movie.service;

import com.theatre.movie.dto.BookingViewDto;
import com.theatre.movie.entity.*;
import com.theatre.movie.form.BookedSeatsForm;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BookingService} class provides methods for manage user bookings/tickets purchases
 * represented by {@link com.theatre.movie.entity.Booking} class
 * Properties: <b>bookingRepo</b>, <b>userRepo</b>, <b>seatRepo</b>, <b>movieSessionRepo</b>
 *
 * @author Hlushchenko Renata
 * @see com.theatre.movie.repository.BookingRepository
 * @see com.theatre.movie.repository.UserRepository
 * @see com.theatre.movie.repository.SeatRepository
 * @see com.theatre.movie.repository.MovieSessionRepository
 */

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingService {
    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class);
    private BookingRepository bookingRepo;
    private UserRepository userRepo;
    private SeatRepository seatRepo;
    private MovieSessionRepository movieSessionRepo;

    /**
     *
     * @param bookedSeatsForm - is used for data transfer for book tickets request
     *      *            represented by {@link com.theatre.movie.form.BookedSeatsForm} class
     * @param username - username of user which want to book tickets
     */
    @Transactional
    public void createBooking(BookedSeatsForm bookedSeatsForm, String username) {
        Integer movieSessionId = Integer.parseInt(bookedSeatsForm.getMovieSessionId());

        for (String seatId : bookedSeatsForm.getBookedSeats()) {
            Booking booking = new Booking(
                    LocalDateTime.now(),
                    userRepo.findByUsername(username),
                    seatRepo.findById(Integer.parseInt(seatId)).get(),
                    movieSessionRepo.findById(movieSessionId).get()
            );
            Booking createdBooking = bookingRepo.save(booking);
            LOG.info("Created new booking: \n{}", createdBooking);
        }
    }

    /**
     * Return information about all users purchases, which has movie session
     * {@link com.theatre.movie.entity.MovieSession} with startAt property >= current time now
     *
     * @param username - username, is used for search data in db
     * @return list of Dtos - stores information about booking which will be displayed
     * for registered user on user-tickets.jsp
     */
    @Transactional(readOnly = true)
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

    /***
     * Extract only necessary data from <tt>booking</tt> and return it in dto
     * for displaying on view page
     */
    private BookingViewDto mapBookingViewDto(Booking booking) {
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
