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
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "hall_id")
    private Hall hall;
    @NonNull
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @NonNull
    private Double price;


}
