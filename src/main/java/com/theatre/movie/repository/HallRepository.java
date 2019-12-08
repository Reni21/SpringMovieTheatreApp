package com.theatre.movie.repository;

import com.theatre.movie.entity.Booking;
import com.theatre.movie.entity.Hall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface HallRepository extends CrudRepository<Hall, Integer> {

}
