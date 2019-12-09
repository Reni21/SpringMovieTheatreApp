package com.theatre.movie.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class BookedSeatsForm {
    String movieSessionId;
    String [] bookedSeats;
}
