package com.theatre.movie.service;

import com.theatre.movie.entity.Role;
import com.theatre.movie.entity.User;
import com.theatre.movie.exception.UserAlreadyExistException;
import com.theatre.movie.form.SignUpForm;
import com.theatre.movie.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public User getByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Transactional
    public User registerUser(SignUpForm signUpForm, Role role) throws UserAlreadyExistException {
        validateUserRequest(signUpForm);
        User user = new User(signUpForm.getUsername().trim(),
                passwordEncoder.encode(signUpForm.getPassword().trim()),
                signUpForm.getEmail().trim(),
                role);
        return userRepo.save(user);
    }

    private void validateUserRequest(SignUpForm signUpForm) throws UserAlreadyExistException {
        String username = signUpForm.getUsername();
        if (userRepo.findByUsername(username) != null) {
            LOG.warn("Find extra match for username=" + username);
            throw new UserAlreadyExistException("username error.username.duplicated");
        }
        String email = signUpForm.getEmail();
        if (userRepo.findByEmail(email) != null) {
            LOG.warn("Find extra match for email=" + email);
            throw new UserAlreadyExistException("email error.email.duplicated");
        }
    }

}
