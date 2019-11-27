package com.theatre.movie.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "booking", uniqueConstraints = @UniqueConstraint(columnNames = {"seat_id", "session_id"}))
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @NonNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
    private Seat bookedSeat;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    private MovieSession movieSession;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.BOOKED;
}
