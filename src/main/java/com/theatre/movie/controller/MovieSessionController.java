package com.theatre.movie.controller;

import com.theatre.movie.dto.MenuDateViewDto;
import com.theatre.movie.dto.MovieSessionViewDto;
import com.theatre.movie.service.MovieSessionService;
import com.theatre.movie.service.WeekScheduleDatesService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
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
    public String createMovieSession(){
        return null;
    }
}
