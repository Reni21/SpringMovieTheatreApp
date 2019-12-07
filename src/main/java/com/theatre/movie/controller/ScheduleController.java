package com.theatre.movie.controller;

import com.theatre.movie.dto.MenuDateViewDto;
import com.theatre.movie.dto.MovieSessionsScheduleViewDto;
import com.theatre.movie.entity.Role;
import com.theatre.movie.exception.InvalidScheduleDateException;
import com.theatre.movie.service.MovieSessionService;
import com.theatre.movie.service.WeekScheduleDatesService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/schedule")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleController {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);
    private MovieSessionService movieSessionService;
    private WeekScheduleDatesService weekScheduleDatesService;

    @GetMapping
    public String getSchedulePage(@RequestParam(value = "date", required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                          LocalDate date,
                                  Authentication authentication,
                                  Model model) {
        LOG.info("Get for /schedule");
        if (date == null) {
            String url = "schedule?date=" + LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            LOG.info("Date param is null. Send redirect to url: {}",url);
            return "redirect:" + url;
        }
        try {
            List<MovieSessionsScheduleViewDto> currentDaySessions = movieSessionService.getMovieSessionsScheduleForDate(date);
            List<MenuDateViewDto> menuDates = weekScheduleDatesService.getWeekScheduleDates(date);

            LOG.info("Current day sessions number: {}\n{}",currentDaySessions.size(), currentDaySessions);
            model.addAttribute("menuDates", menuDates);
            model.addAttribute("sessions", currentDaySessions);
            model.addAttribute("activeTab", "schedule");

            if (isAdmin(authentication)) {
                return "schedule-admin";
            }
            return "schedule";
        } catch (InvalidScheduleDateException e) {
            LOG.warn("Get movie schedule request failed: {}", e.getMessage());
            return "404-error";
        }
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication != null && authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::valueOf)
                .anyMatch(Role.ROLE_ADMIN::equals);
    }
}
