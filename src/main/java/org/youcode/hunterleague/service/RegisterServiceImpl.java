package org.youcode.hunterleague.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.exception.user.DuplicateUserException;
import org.youcode.hunterleague.repository.UserRepository; // Make sure to import your UserRepository

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {


    private final UserRepository userRepository;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public User registerUser(User user) {

        Optional<User> existingUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateUserException("Username or email already exists.");
        }
        return userRepository.save(user);

    };



}
