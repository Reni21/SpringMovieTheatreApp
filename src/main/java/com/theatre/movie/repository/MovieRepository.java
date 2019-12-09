package com.theatre.movie.repository;

import com.theatre.movie.entity.Movie;
import com.theatre.movie.entity.MovieSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
