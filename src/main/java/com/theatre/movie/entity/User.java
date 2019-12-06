package com.theatre.movie.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Integer id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
