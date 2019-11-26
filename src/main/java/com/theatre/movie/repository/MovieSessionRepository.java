package com.theatre.movie.repository;

import com.theatre.movie.entity.MovieSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionRepository extends CrudRepository<MovieSession, Integer> {
    @Query("SELECT ms FROM MovieSession ms WHERE ms.startAt BETWEEN (:searchFrom) AND (:searchTo)")
    public List<MovieSession> getAllInRange(@Param("searchFrom") LocalDateTime searchFrom,
                                            @Param("searchTo") LocalDateTime searchTo);
}
