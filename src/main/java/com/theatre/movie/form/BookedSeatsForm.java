package com.theatre.movie.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class BookedSeatsForm {
    String movieSessionId;
    @NotEmpty
    String [] bookedSeats;
}
