package com.theatre.movie.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "seat", uniqueConstraints = @UniqueConstraint(columnNames = {"seat_row", "place", "hall_id"}))
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;
    @NonNull
    @Column(name = "seat_row")
    private Integer row;
    @NonNull
    private Integer place;
    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "hall_id")
    private Hall hall;
}
