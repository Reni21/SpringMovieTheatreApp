package com.theatre.movie.controller;

import com.theatre.movie.dto.MovieSimpleViewDto;
import com.theatre.movie.service.MovieService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);
    private MovieService movieService;

    @GetMapping("/movies-preview")
    public List<MovieSimpleViewDto> getMovies() {
        LOG.info("Start ajax GET");
        return movieService.getAllSimpleView();
    }
}
