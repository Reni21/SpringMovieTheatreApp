package com.theatre.movie.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class MovieSessionsScheduleViewDto {
    private Integer movieId;
    @NonNull
    private String title;
    @NonNull
    private Integer duration;
    @Setter
    private List<MovieSessionTimeViewDto> movieSessionTimes = new ArrayList<>();
    @Setter
    private String trailerUrl;
    @Setter
    private String backgroundImgPath;
    @Setter
    private String coverImgPath;

    public void addMovieSessionTimeDto(MovieSessionTimeViewDto timeDto){
        movieSessionTimes.add(timeDto);
    }

}
