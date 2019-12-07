package com.theatre.movie.form.dto;

import com.theatre.movie.entity.BookingStatus;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class CreateBookingDto {
    private Integer bookingId;
    @NonNull
    private LocalDateTime createdAt;
    @NonNull
    private Integer userId;
    @NonNull
    private Integer bookedSeatId;
    @NonNull
    private Integer movieSessionId;

    private BookingStatus bookingStatus = BookingStatus.BOOKED;
}
