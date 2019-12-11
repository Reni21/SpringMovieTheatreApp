package com.theatre.movie.security;

import com.theatre.movie.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthUser {
    public String username;
    public Role role;
}