package com.theatre.movie.form;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SignUpForm {
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    @Size(min = 1, max = 15, message = "{error.username.sizerange}")
    private String username;
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    @Size(min = 5, max = 20, message = "{error.password.sizerange}")
    private String password;
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    @Email(message = "{error.email.notvalide.format}")
    private String email;
}
