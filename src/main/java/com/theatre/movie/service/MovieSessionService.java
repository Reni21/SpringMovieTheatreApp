package com.theatre.movie.service;


import com.theatre.movie.dto.BookedSeatViewDto;
import com.theatre.movie.dto.MovieSessionTimeViewDto;
import com.theatre.movie.dto.MovieSessionViewDto;
import com.theatre.movie.dto.MovieSessionsScheduleViewDto;
import com.theatre.movie.entity.Hall;
import com.theatre.movie.entity.Movie;
import com.theatre.movie.entity.MovieSession;
import com.theatre.movie.entity.Seat;
import com.theatre.movie.exception.InvalidScheduleDateException;
import com.theatre.movie.exception.MovieScheduleRemovalException;
import com.theatre.movie.exception.MovieSessionCreationException;
import com.theatre.movie.form.MovieSessionForm;
import com.theatre.movie.repository.BookingRepository;
import com.theatre.movie.repository.HallRepository;
import com.theatre.movie.repository.MovieRepository;
import com.theatre.movie.repository.MovieSessionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieSessionService {
    private static final Logger LOG = LoggerFactory.getLogger(MovieSessionService.class);
    private static final int BREAK_DURATION = 15;

    private MovieSessionRepository movieSessionRepo;
    private BookingRepository bookingRepo;
    private MovieRepository movieRepo;
    private HallRepository hallRepo;

    @Transactional(readOnly = true)
    public MovieSessionViewDto getMovieSessionById(int id) {
        MovieSession movieSession = movieSessionRepo.findById(id).get();
        Set<Integer> bookedSeats = bookingRepo.getAllBookedSeatsIdByMovieSessionId(id);
        LOG.info("Extracted bookedSeats id: {}", bookedSeats);
        Hall hall = movieSession.getHall();
        Movie movie = movieSession.getMovie();
        Map<Integer, List<BookedSeatViewDto>> seats = mapBookedSeats(hall.getSeats(), bookedSeats);
        MovieSessionViewDto dto = new MovieSessionViewDto(
                movieSession,
                movie.getTitle(),
                movie.getDurationMinutes(),
                hall.getHallName(), seats
        );
        dto.setBookedSeatsCount(bookedSeats.size());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<MovieSessionsScheduleViewDto> getMovieSessionsScheduleForDate(LocalDate date)
            throws InvalidScheduleDateException {

        checkScheduleDateInValidRange(date);

        LocalDateTime searchFrom = LocalDate.now().equals(date) ? date.atTime(LocalTime.now()) : date.atStartOfDay();
        LocalDateTime searchTo = date.atTime(LocalTime.MAX);

        LOG.info("Sessions search start from: " + searchFrom + " to: " + searchTo);
        List<MovieSession> sessions = movieSessionRepo.getAllInRange(
                searchFrom, searchTo);
        sessions.forEach(System.out::println);
        Map<Movie, List<MovieSession>> sessionsByMovie = sessions.stream()
                .collect(Collectors.groupingBy(MovieSession::getMovie));

        return sessionsByMovie.entrySet().stream()
                .map(movieListEntry -> {
                    Movie movie = movieListEntry.getKey();
                    MovieSessionsScheduleViewDto scheduleDto = new MovieSessionsScheduleViewDto(movie.getTitle(), movie.getDurationMinutes());
                    scheduleDto.setTrailerUrl(movie.getTrailerUrl());
                    scheduleDto.setBackgroundImgPath(movie.getBackgroundImgUrl());
                    scheduleDto.setCoverImgPath(movie.getCoverImgUrl());
                    List<MovieSessionTimeViewDto> timeDtos = mapMovieSessionTimeDtos(movieListEntry.getValue());
                    scheduleDto.setMovieSessionTimes(timeDtos);
                    scheduleDto.setMovieId(movie.getMovieId());
                    return scheduleDto;
                }).collect(Collectors.toList());
    }

    @Transactional
    public MovieSession addMovieSession(MovieSessionForm movieSessionForm, String dateStr, String movieId) throws MovieSessionCreationException {
        LOG.info("Create new movie session for data: " + movieSessionForm);

        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        LocalTime time = LocalTime.of(movieSessionForm.getHours(), movieSessionForm.getMinutes());
        LocalDateTime startAt = LocalDateTime.of(date, time);
        if (startAt.isBefore(LocalDateTime.now())) {
            throw new MovieSessionCreationException("Required time already passed.");
        }
        Movie movie = movieRepo.findById(Integer.parseInt(movieId)).get();
        MovieSession movieSession = new MovieSession(
                movie,
                hallRepo.findById(1).get(),
                startAt,
                movieSessionForm.getPrice());
        validateMovieSessionTimeRange(movieSessionForm, movie.getDurationMinutes(), dateStr);
        return movieSessionRepo.save(movieSession);
    }

    @Transactional
    public void deleteMovieSessionByIds(List<String> sessionsIds) throws MovieScheduleRemovalException {
        for (String id : sessionsIds) {
            boolean bookingExists = bookingRepo.isBookingForMovieSessionExist(Integer.parseInt(id));
            if (bookingExists) {
                LOG.info("Exist booking for required movie session:{}", id);
                throw new MovieScheduleRemovalException("Movie card can not be deleted. Some sessions already have reservations.");
            }
        }
        sessionsIds.forEach(id -> {
            movieSessionRepo.deleteById(Integer.parseInt(id));
        });

    }

    private void checkScheduleDateInValidRange(LocalDate date) throws InvalidScheduleDateException {
        LocalDate now = LocalDate.now();
        if (date.isBefore(now) || date.isAfter(now.plusDays(7))) {
            throw new InvalidScheduleDateException(
                    "Date " + date.format(DateTimeFormatter.ISO_DATE) + " is in wrong range");
        }
    }

    private List<MovieSessionTimeViewDto> mapMovieSessionTimeDtos(List<MovieSession> movieSessions) {
        return movieSessions.stream()
                .map(session -> new MovieSessionTimeViewDto(
                        session.getSessionId(),
                        session.getStartAt().toLocalTime()))
                .collect(Collectors.toList());
    }

    private Map<Integer, List<BookedSeatViewDto>> mapBookedSeats(List<Seat> allSeats, Set<Integer> bookedSeats) {
        return allSeats.stream().map(seat -> {
            BookedSeatViewDto dto = new BookedSeatViewDto(seat.getSeatId(), seat.getRow(), seat.getPlace());
            if (bookedSeats.contains(seat.getSeatId())) {
                dto.setBooked(true);
            }
            return dto;
        }).collect(Collectors.groupingBy(BookedSeatViewDto::getRow));
    }

    private void validateMovieSessionTimeRange(MovieSessionForm movieSessionForm, int newMovieDuration, String dateStr) throws MovieSessionCreationException {
        LocalDateTime newMovieStartAt = getSessionStartAt(movieSessionForm, dateStr);

        LocalDateTime from = newMovieStartAt.toLocalDate().atStartOfDay();
        LocalDateTime to = newMovieStartAt.plusMinutes(newMovieDuration + BREAK_DURATION);

        List<MovieSession> movieSessions = movieSessionRepo.getAllInRange(from, to);

        StringJoiner errors = new StringJoiner(";");

        for (MovieSession movieSession : movieSessions) {
            int movieDuration = movieSession.getMovie().getDurationMinutes();
            LocalDateTime movieStartAt = movieSession.getStartAt();

            if (newMovieStartAt.isEqual(movieStartAt)
                    || (newMovieStartAt.isAfter(movieStartAt) && newMovieStartAt.isBefore(movieStartAt.plusMinutes(movieDuration + BREAK_DURATION)))
                    || (newMovieStartAt.isBefore(movieStartAt) && newMovieStartAt.plusMinutes(newMovieDuration + BREAK_DURATION).isAfter(movieStartAt))) {
                errors.add(String.format("%s duration %d", movieStartAt.format(DateTimeFormatter.ofPattern("HH:mm")), movieDuration));
            }
        }

        if (errors.length() != 0) {
            throw new MovieSessionCreationException("Conflict session times: " + errors.toString());
        }

    }

    private LocalDateTime getSessionStartAt(MovieSessionForm movieSessionForm, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        LocalTime time = LocalTime.of(movieSessionForm.getHours(), movieSessionForm.getMinutes());
        return LocalDateTime.of(date, time);
    }

}
