package com.theatre.movie.form;


import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class LoginForm {
    @NotNull
    private String username;
    private String password;
}
