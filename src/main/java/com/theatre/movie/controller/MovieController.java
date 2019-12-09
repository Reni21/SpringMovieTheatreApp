package com.theatre.movie.controller;

import com.theatre.movie.dto.MovieSimpleViewDto;
import com.theatre.movie.dto.PaginatedData;
import com.theatre.movie.entity.Movie;
import com.theatre.movie.exception.MovieRemovalException;
import com.theatre.movie.service.MovieService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);
    private static final int MOVIES_PER_PAGE = 3;
    private MovieService movieService;

    @GetMapping("/movies-preview")
    @ResponseBody
    public List<MovieSimpleViewDto> getMovies() {
        LOG.info("Start ajax GET");
        return movieService.getAllSimpleView();
    }

    @GetMapping("/movie")
    public String getFullMovies(@RequestParam String page, Model model) {
        LOG.info("Start GET full movies for page=" + page);

        model.addAttribute("activeTab", "movies");
        PaginatedData<Movie> paginatedMovies = movieService.getAll(Integer.parseInt(page), MOVIES_PER_PAGE);
        model.addAttribute("movies", paginatedMovies.getData());
        model.addAttribute("pagesCount", paginatedMovies.getPagesCount());
        model.addAttribute("currentPage", paginatedMovies.getCurrentPage());
        return "admin-movies";
    }

    @DeleteMapping("/movie")
    @ResponseBody
    public ResponseEntity<Object> deleteMovie(@RequestParam String id, Model model) {
        try {
            LOG.info("Try delete movie with id=" + id + " and all its sessions");
            movieService.deleteMovieAndSessions(Integer.parseInt(id));
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        } catch (MovieRemovalException ex) {
            LOG.error("Failed to delete movie." + ex);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Something went wrong");
        }
    }

    @PostMapping("/movie")
    public String createNewMovie(@RequestParam String id, Model model) {
        return null;
    }
}
