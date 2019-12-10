package com.theatre.movie.form;


import lombok.*;

import javax.validation.constraints.*;

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
    @Pattern.List({
            @Pattern(regexp = "(.*[A-Z].*)", message = "{error.password.uppercaseLetter}"),
            @Pattern(regexp = "(.*[0-9].*)", message = "{error.password.digit}")
    })
    private String password;
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    @Email(message = "{error.email.notvalide.format}")
    private String email;
}
