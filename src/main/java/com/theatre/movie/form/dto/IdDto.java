package com.theatre.movie.form.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IdDto {
    List<String> sessionsIds;
}
