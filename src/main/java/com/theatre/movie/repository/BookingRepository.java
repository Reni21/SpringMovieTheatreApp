package com.theatre.movie.repository;

import com.theatre.movie.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    @Query(value = "SELECT seat_id FROM `booking` WHERE session_id = (:sessionId) AND (status = 'BOOKED' OR status = 'PAID')", nativeQuery = true)
    Set<Integer> getAllBookedSeatsIdByMovieSessionId(@Param("sessionId") Integer sessionId);


    @Query(value = "SELECT * FROM `booking` b JOIN `movie_session` ms ON b.session_id = ms.session_id " +
            "WHERE user_id = (:userId) " +
            "AND ms.start_at >= CURRENT_TIMESTAMP() " +
            "AND (status = 'BOOKED' OR status = 'PAID')", nativeQuery = true)
    List<Booking> getAllActualBookingByUserId(Integer userId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN 'true' ELSE 'false' END FROM Booking b " +
            "JOIN MovieSession ms ON b.movieSession.sessionId = ms.sessionId " +
            "WHERE b.movieSession.sessionId = (:movieSessionId)")
    boolean isBookingForMovieSessionExist(Integer movieSessionId);
}
