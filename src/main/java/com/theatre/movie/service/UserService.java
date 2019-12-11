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

/**
 * The {@code UserService} class provides methods for manage information about users
 * represented by {@link com.theatre.movie.entity.User} class
 * Properties: <b>userRepo</b>, <b>passwordEncoder</b>,
 *
 * @author Hlushchenko Renata
 * @see com.theatre.movie.repository.UserRepository
 * @see org.springframework.security.crypto.password.PasswordEncoder
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
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

    /**
     * The method validate all required data for new user creation
     * represented by {@link com.theatre.movie.form.SignUpForm} class
     *
     * @param signUpForm - is used for data transfer for create new user request
     * @throws UserAlreadyExistException if <tt>username</tt> or <tt>email</tt> already exist in db
     */
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
