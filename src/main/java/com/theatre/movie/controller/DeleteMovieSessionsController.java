package com.theatre.movie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theatre.movie.service.MovieSessionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteMovieSessionsController {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteMovieSessionsController.class);
    private MovieSessionService movieSessionService;

    @PostMapping("/delete-session")
    public ResponseEntity<Object> deleteMovieSession(@RequestBody String sessionsIds) {
        LOG.info("Do delete for movie-session ids:{}", sessionsIds);
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> idsForDelete = Arrays.asList(mapper.readValue(sessionsIds, Integer[].class));
            movieSessionService.deleteMovieSessionByIds(idsForDelete);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        } catch (Exception e) {
            LOG.error("Error while delete movie session:", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }
}