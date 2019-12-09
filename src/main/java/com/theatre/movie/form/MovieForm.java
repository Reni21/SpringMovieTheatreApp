package com.theatre.movie.form;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MovieForm {
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    private String title;
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    private String directed;
    @NotNull(message = "{error.notnull}")
    private Integer duration;
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    private String cover;
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    private String bg;
    @NotNull(message = "{error.notnull}")
    @NotEmpty(message = "{error.notempty}")
    private String trailer;
}
