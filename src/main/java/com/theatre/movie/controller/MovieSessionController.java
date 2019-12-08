package com.theatre.movie.controller;

import com.theatre.movie.dto.MenuDateViewDto;
import com.theatre.movie.dto.MovieSessionTimeViewDto;
import com.theatre.movie.dto.MovieSessionViewDto;
import com.theatre.movie.entity.MovieSession;
import com.theatre.movie.form.MovieSessionForm;
import com.theatre.movie.service.MovieSessionService;
import com.theatre.movie.service.WeekScheduleDatesService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/movie-session")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieSessionController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieSessionController.class);
    private WeekScheduleDatesService weekScheduleDatesService;
    private MovieSessionService movieSessionService;

    @GetMapping("{movieSessionId}")
    public String getMovieSessionPage(@PathVariable Integer movieSessionId, Model model) {
        LOG.info("PathVariable movieSessionId={}", movieSessionId);
        model.addAttribute("activeTab", "null");
        List<MenuDateViewDto> menuDates = weekScheduleDatesService.getWeekScheduleDates(LocalDate.now());
        model.addAttribute("menuDates", menuDates);

        MovieSessionViewDto movieSession = movieSessionService.getMovieSessionById(movieSessionId);
        model.addAttribute("movieSession", movieSession);
        LOG.info("Extracted movie session:\n{}", movieSession);
        return "seats-booking";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> createMovieSession(@Valid @ModelAttribute MovieSessionForm movieSessionForm,
                                                     BindingResult bindingResult,
                                                     @RequestParam String movieId,
                                                     @RequestParam String date) {
        LOG.info("Perform post for movie id={} and movie session data:{}\n form:\n{}", movieId, date, movieSessionForm);
        List<String> errorsMsg = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    String field = fieldError.getField();
                    String msg = fieldError.getCode();
                    LOG.info("field={}, msg: {}", field, msg);
                    errorsMsg.add(msg);
                }
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMsg);
        }

        try {
            MovieSession movieSession = movieSessionService.addMovieSession(movieSessionForm, date, movieId);
            MovieSessionTimeViewDto movieSessionTime = new MovieSessionTimeViewDto(
                    movieSession.getSessionId(), movieSession.getStartAt().toLocalTime());
            return ResponseEntity.status(HttpStatus.OK).body(movieSessionTime);
        } catch (Exception ex) {
            LOG.error("Failed to create new movie session:" + ex);
            errorsMsg.add("Failed to create new movie session");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMsg);
        }
    }

}
