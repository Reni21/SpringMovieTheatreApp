package com.theatre.movie.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "hall")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private Integer hallId;
    @NonNull
    @Column(name = "name", unique = true)
    private String hallName;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "hall",
            cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat){
        seats.add(seat);
    }

}
