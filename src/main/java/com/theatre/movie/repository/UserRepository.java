package com.theatre.movie.repository;

import com.theatre.movie.entity.Movie;
import com.theatre.movie.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


    User findByUsernameAndPassword(String login, String password);

    User findByUsername(String login);
}
