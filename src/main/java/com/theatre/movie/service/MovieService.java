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

/**
 * The {@code MovieService} class provides methods for manage information about all  movies
 * represented by {@link com.theatre.movie.entity.Movie} class
 * Properties: <b>movieRepo</b>, <b>movieSessionRepo</b>,
 * <b>bookingRepo</b>
 *
 * @author Hlushchenko Renata
 * @see com.theatre.movie.repository.MovieRepository;
 * @see com.theatre.movie.repository.MovieSessionRepository;
 * @see com.theatre.movie.repository.BookingRepository;
 */

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private MovieRepository movieRepo;
    private MovieSessionRepository movieSessionRepo;
    private BookingRepository bookingRepo;

    /**
     * @param movieForm - is used for data transfer for create new movie request
     *            represented by {@link com.theatre.movie.form.MovieForm} class
     * @return {@link com.theatre.movie.entity.Movie}
     */
    @Transactional
    public Movie createMovie(MovieForm movieForm) {
        Movie movie = new Movie(movieForm.getTitle(), movieForm.getDirected(), movieForm.getDuration());
        movie.setTrailerUrl(movieForm.getTrailer());
        movie.setCoverImgUrl(movieForm.getCover());
        movie.setBackgroundImgUrl(movieForm.getBg());
        return movieRepo.save(movie);
    }

    /**
     * Return part of data about movie. Will be displaying
     * for admin-user on admin-movies.jsp
     *
     * @return list of Dtos - stores part of information about all movies
     */
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

    /**
     * The method extract movies for display on required <tt>page</tt>
     *
     * @param page          - number of current page
     * @param moviesPerPage - number of movies card per page
     * @return - store extracted movies for required <tt>page</tt>
     */
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

    /**
     * The method delete required movie and all movie sessions which mapped on it
     *
     * @param movieId - movie id, is used for search data in db
     * @throws MovieRemovalException if some actual movie sessions which has mapped for
     *                               required movie has booking
     */
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
