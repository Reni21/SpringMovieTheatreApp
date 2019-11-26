package com.theatre.movie.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "movie_session")
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Integer sessionId;
    @NonNull
    @Column(name = "movie_id")
    private Integer movieId;
    @NonNull
    @Column(name = "hall_id")
    private Integer hallId;
    @NonNull
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @NonNull
    private Double price;


}
