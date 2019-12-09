package com.theatre.movie.controller;

import com.theatre.movie.dto.MenuDateViewDto;
import com.theatre.movie.dto.MovieSessionTimeViewDto;
import com.theatre.movie.dto.MovieSessionViewDto;
import com.theatre.movie.entity.MovieSession;
import com.theatre.movie.exception.MovieScheduleRemovalException;
import com.theatre.movie.exception.MovieSessionCreationException;
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
import java.util.Arrays;
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
            errorsMsg = collectErrorMessages(bindingResult);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorsMsg);
        }

        try {
            MovieSession movieSession = movieSessionService.addMovieSession(movieSessionForm, date, movieId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapMovieSessionTimeViewDto(movieSession));

        } catch (MovieSessionCreationException ex) {
            LOG.error(ex.getMessage());
            errorsMsg.add(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorsMsg);
        } catch (Exception ex) {
            String msg = "Failed to create new movie session";
            LOG.error(msg, ex);
            errorsMsg.add(msg);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorsMsg);
        }
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Object> deleteMovieSession(@RequestParam(value = "param") String[] sessionsIds) {
        LOG.info("Do delete for movie-session ids:{}", (Object) sessionsIds);
        try {
            movieSessionService.deleteMovieSessionByIds(Arrays.asList(sessionsIds));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(null);

        } catch (MovieScheduleRemovalException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());

        } catch (Exception e) {
            LOG.error("Error while delete movie session:", e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Something went wrong");
        }
    }

    private MovieSessionTimeViewDto mapMovieSessionTimeViewDto(MovieSession movieSession) {
        return new MovieSessionTimeViewDto(
                movieSession.getSessionId(), movieSession.getStartAt().toLocalTime());
    }

    private List<String> collectErrorMessages(BindingResult bindingResult) {
        List<String> errorsMsg = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> {

            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String field = fieldError.getField();
                String msg = fieldError.getCode();
                LOG.info("field={}, msg: {}", field, msg);
                errorsMsg.add(msg);
            }
        });
        return errorsMsg;
    }

}
