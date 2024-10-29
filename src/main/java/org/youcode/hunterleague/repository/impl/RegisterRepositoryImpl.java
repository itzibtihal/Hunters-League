package org.youcode.hunterleague.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.repository.RegisterRepository;
import org.youcode.hunterleague.repository.UserRepository;
import org.youcode.hunterleague.repository.dto.RegisterDTO;
import org.youcode.hunterleague.repository.dto.mapper.UserMapper;

import java.util.Optional;

@Repository
public class RegisterRepositoryImpl implements RegisterRepository {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public RegisterRepositoryImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User registerUser(RegisterDTO registerDTO) {

        User user = userMapper.toUser(registerDTO);

        return userRepository.save(user);
    }


    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }
}
