package com.theatre.movie.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;
    @NonNull
    @Column(unique = true)
    private String title;
    @NonNull
    @Column(name = "directed_by")
    private String directedBy;
    private String description;
    @NonNull
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
    @Column(name = "trailer_url")
    private String trailerUrl;
    @Column(name = "background_img_url")
    private String backgroundImgUrl;
    @Column(name = "cover_img_url")
    private String coverImgUrl;
}
