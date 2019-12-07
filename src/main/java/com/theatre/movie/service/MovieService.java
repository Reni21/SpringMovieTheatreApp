package com.theatre.movie.service;

import com.theatre.movie.dto.MovieSimpleViewDto;
import com.theatre.movie.entity.Movie;
import com.theatre.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private MovieRepository movieRepo;

    public List<MovieSimpleViewDto> getAllSimpleView(){
        LOG.info("Extract simple movie view data");
        List<Movie> movies = (List<Movie>) movieRepo.findAll();
        List<MovieSimpleViewDto> dtos = new ArrayList<>();
        movies.forEach(movie -> {
            dtos.add(new MovieSimpleViewDto(movie.getMovieId(), movie.getTitle(), movie.getDurationMinutes() ));
        });
        LOG.info("Extracted MovieSimpleViewDto:\n{}", dtos);
        return dtos;
    }
}
