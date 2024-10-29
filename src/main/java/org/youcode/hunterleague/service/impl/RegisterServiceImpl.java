package org.youcode.hunterleague.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.exception.user.DuplicateUserException;
import org.youcode.hunterleague.exception.user.InvalidUserDataException;
import org.youcode.hunterleague.repository.RegisterRepository;
import org.youcode.hunterleague.repository.UserRepository;
import org.youcode.hunterleague.repository.dto.RegisterDTO;
import org.youcode.hunterleague.service.RegisterService;

@Service
public class RegisterServiceImpl extends RegisterService {

    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterServiceImpl(RegisterRepository registerRepository, UserRepository userRepository) {
        super(userRepository); // Call the parent constructor
        this.registerRepository = registerRepository;
    }

    @Override
    public User registerUser(RegisterDTO registerDTO) throws DuplicateUserException, InvalidUserDataException {
        validateRegisterDTO(registerDTO);
        return registerRepository.registerUser(registerDTO); // Ensure this method is defined in RegisterRepository
    }
}
