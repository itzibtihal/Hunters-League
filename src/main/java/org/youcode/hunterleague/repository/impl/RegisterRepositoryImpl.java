package org.youcode.hunterleague.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.repository.RegisterRepository;
import org.youcode.hunterleague.repository.UserRepository;

import java.util.Optional;

@Repository
public class RegisterRepositoryImpl implements RegisterRepository {

    private final UserRepository userRepository;


    @Autowired
    public RegisterRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }
}
