package com.theatre.movie.repository;

import com.theatre.movie.entity.Booking;
import com.theatre.movie.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    @Query(value = "SELECT seat_id FROM `booking` WHERE session_id = (:sessionId) AND (status = 'BOOKED' OR status = 'PAID')", nativeQuery = true)
    public Set<Integer> getAllBookedSeatsIdByMovieSessionId(@Param("sessionId") Integer sessionId);

}
