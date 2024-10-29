package org.youcode.hunterleague.service;

import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.exception.user.DuplicateUserException;
import org.youcode.hunterleague.exception.user.InvalidUserDataException;
import org.youcode.hunterleague.repository.UserRepository; // Make sure to import your UserRepository
import org.youcode.hunterleague.repository.dto.RegisterDTO;

import java.util.Optional;

public abstract class RegisterService {

    protected final UserRepository userRepository; // Declare userRepository

    // Constructor for dependency injection
    protected RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public abstract User registerUser(RegisterDTO registerDTO) throws DuplicateUserException, InvalidUserDataException;

    protected void validateRegisterDTO(RegisterDTO registerDTO) throws DuplicateUserException, InvalidUserDataException {
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail());

        if (existingUser.isPresent()) {
            throw new DuplicateUserException("Username or email already exists.");
        }

        // Add any additional validations here if necessary
    }
}
