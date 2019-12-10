package com.theatre.movie.service;

import com.theatre.movie.dto.MovieSimpleViewDto;
import com.theatre.movie.dto.PaginatedData;
import com.theatre.movie.entity.Movie;
import com.theatre.movie.exception.MovieRemovalException;
import com.theatre.movie.form.MovieForm;
import com.theatre.movie.repository.BookingRepository;
import com.theatre.movie.repository.MovieRepository;
import com.theatre.movie.repository.MovieSessionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private MovieRepository movieRepo;
    private MovieSessionRepository movieSessionRepo;
    private BookingRepository bookingRepo;

    @Transactional
    public Movie createMovie(MovieForm movieForm) {
        Movie movie = new Movie(movieForm.getTitle(), movieForm.getDirected(), movieForm.getDuration());
        movie.setTrailerUrl(movieForm.getTrailer());
        movie.setCoverImgUrl(movieForm.getCover());
        movie.setBackgroundImgUrl(movieForm.getBg());
        return movieRepo.save(movie);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public PaginatedData<Movie> getAll(int page, int moviesPerPage) {
        LOG.info("Extract all movies");
        Pageable paging = PageRequest.of(page - 1, moviesPerPage, Sort.Direction.ASC, "movieId");
        LOG.info("Pageable data for search: {}", paging);
        List<Movie> movies = movieRepo.findAll(paging);
        long moviesCount = movieRepo.count();
        int pagesCount = (int) Math.ceil(moviesCount * 1.0 / moviesPerPage);
        LOG.info("Total moviesCount={}, extracted count={}, pagesCount={}", moviesCount, movies.size(), pagesCount);
        return new PaginatedData<>(movies, pagesCount, page);
    }

    @Transactional
    public void deleteMovieAndSessions(int movieId) {
            boolean bookingExists = bookingRepo.isBookingForMovieExist(movieId);
            if (bookingExists) {
                throw new MovieRemovalException("Movie can not be deleted. Still exist booking for movie sessions with this movie.");
            }
            movieSessionRepo.deleteAllByMovieId(movieId);
            movieRepo.deleteById(movieId);
    }
}
