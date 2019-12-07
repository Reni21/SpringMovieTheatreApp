package com.theatre.movie.repository;

import com.theatre.movie.entity.Booking;
import com.theatre.movie.entity.Seat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SeatRepository extends CrudRepository<Seat, Integer> {


}
