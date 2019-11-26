//package com.theatre.movie.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString
//@Entity
//@Table(name = "booking", uniqueConstraints = @UniqueConstraint(columnNames = "password, email"))
//public class Booking {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "booking_id")
//    private Integer bookingId;
//
//    @NonNull
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
//    private LocalDateTime createdAt;
//
//    @NonNull
//    @Column(name = "user_id")
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private User user;
//
//    @NonNull
//    @Column(name = "seat_id")
//    @OneToOne
//    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
//    private Seat bookedSeat;
//
//    @NonNull
//    @Column(name = "session_id")
//    @OneToOne
//    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
//    private MovieSession movieSession;
//
//    @Column(name = "status")
//    @Enumerated(value = EnumType.STRING)
//    private BookingStatus bookingStatus = BookingStatus.BOOKED;
//}
