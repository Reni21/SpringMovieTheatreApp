package com.theatre.movie.controller;

import com.theatre.movie.dto.MenuDateViewDto;
import com.theatre.movie.dto.MovieSessionsScheduleViewDto;
import com.theatre.movie.entity.Role;
import com.theatre.movie.entity.User;
import com.theatre.movie.exception.InvalidScheduleDateException;
import com.theatre.movie.service.MovieSessionService;
import com.theatre.movie.service.WeekScheduleDatesService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/schedule")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleController {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);
    private MovieSessionService sessionService;
    private WeekScheduleDatesService weekScheduleDatesService;

    @GetMapping
    public String getSchedulePage(HttpServletRequest request, Model model) {
        String dateStr = request.getParameter("date");
        LocalDate now = LocalDate.now();
        LocalDate date = dateStr == null ? now : LocalDate.parse(dateStr);
        try {
            List<MovieSessionsScheduleViewDto> currentDaySessions = sessionService.getMovieSessionsScheduleForDate(date);
            List<MenuDateViewDto> menuDates = weekScheduleDatesService.getWeekScheduleDates(date);

            LOG.info("Current day sessions number: " + currentDaySessions.size() + "\n" + currentDaySessions);
            model.addAttribute("menuDates", menuDates);
            model.addAttribute("sessions", currentDaySessions);
            model.addAttribute("activeTab", "schedule");

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null && Role.ROLE_ADMIN.equals(user.getRole())) {
                return "admin-schedule";
            }
            return "schedule";
        } catch (InvalidScheduleDateException e) {
            LOG.warn("Get movie schedule request failed: " + e.getMessage());
            return "404-error";
        }
    }
}
