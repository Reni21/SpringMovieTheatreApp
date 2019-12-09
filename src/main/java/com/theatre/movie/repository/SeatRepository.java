package com.theatre.movie.repository;

import com.theatre.movie.entity.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Integer> {

}
