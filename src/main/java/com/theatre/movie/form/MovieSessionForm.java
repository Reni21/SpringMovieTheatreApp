package com.theatre.movie.form;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MovieSessionForm {
    @NotNull(message = "{error.notnull}")
    @Min(value = 9, message = "{error.hours.range}")
    @Max(value = 22, message = "{error.hours.range}")
    private Integer hours;
    @NotNull(message = "{error.notnull}")
    @Min(value = 0,  message = "{error.minutes.range}")
    @Max(value = 59,  message = "{error.minutes.range}")
    private Integer minutes;
    @NotNull(message = "{error.notnull}")
    @PositiveOrZero(message = "{error.price.min}")
    private Double price;
}
