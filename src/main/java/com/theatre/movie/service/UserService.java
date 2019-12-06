package com.theatre.movie.service;

import com.theatre.movie.entity.User;
import com.theatre.movie.form.LoginForm;
import com.theatre.movie.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepo;

    public User getByUsername(String username) {
        return userRepo.findByUsername(username);
    }

//    public User addUser(CreateUserRequestDto userRequest) throws UserAlreadyExistException {
//        validateUserRequest(userRequest);
//        //todo: add pass hashing
//        User user = new User(userRequest.getLogin(), userRequest.getPassword(), userRequest.getEmail(), Role.ROLE_USER);
//        return userRepo.create(user);
//    }
//
//    private void validateUserRequest(CreateUserRequestDto userRequest) throws UserAlreadyExistException {
//        if (isEmpty(userRequest.getLogin()) || isEmpty(userRequest.getPassword()) || isEmpty(userRequest.getEmail())) {
//            throw new IllegalArgumentException("Required data is empty.");
//        }
//        String login = userRequest.getLogin();
//        if (userRepo.isLoginExist(login)) {
//            LOG.warn("Find extra match for login=" + login);
//            throw new UserAlreadyExistException("This username is already occupied.");
//        }
//        String email = userRequest.getEmail();
//        if (userRepo.isEmailExist(email)) {
//            LOG.warn("Find extra match for email=" + email);
//            throw new UserAlreadyExistException("This email is already occupied.");
//        }
//    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
