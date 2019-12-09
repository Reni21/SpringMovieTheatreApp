package com.theatre.movie.repository;

import com.theatre.movie.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findAll(Pageable pageable);

}
