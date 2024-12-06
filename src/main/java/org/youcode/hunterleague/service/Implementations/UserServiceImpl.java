package org.youcode.hunterleague.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.repository.UserRepository;
import org.youcode.hunterleague.repository.UserSearchRepository;
import org.youcode.hunterleague.service.DTOs.SearchUserDTO;
import org.youcode.hunterleague.service.UserService;
import org.youcode.hunterleague.web.exception.InvalidCredentialsException;
import org.youcode.hunterleague.web.exception.user.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserSearchRepository userSearchRepository;


    @Override
    public Page<User> getAllUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public Boolean deleteUser(UUID id) {
        if (id == null) {
            throw new InvalidCredentialsException("Invalid user id");
        }
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<User> searchUsers(SearchUserDTO searchUserDTO){
        return userSearchRepository.findByCriteria(searchUserDTO);
    }

    @Override
    public User updateUser(UUID id,User user){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userToUpdate.setUsername(user.getUsername() != null ? user.getUsername() : userToUpdate.getUsername());
        userToUpdate.setFirstName(user.getFirstName() != null ? user.getFirstName() : userToUpdate.getFirstName());
        userToUpdate.setLastName(user.getLastName() != null ? user.getLastName() : userToUpdate.getLastName());
        userToUpdate.setEmail(user.getEmail() != null ? user.getEmail() : userToUpdate.getEmail());
        userToUpdate.setNationality(user.getNationality() != null ? user.getNationality() : userToUpdate.getNationality());
        userToUpdate.setLicenseExpirationDate(user.getLicenseExpirationDate() != null ? user.getLicenseExpirationDate() : userToUpdate.getLicenseExpirationDate());

        return userRepository.save(userToUpdate);
    }

    @Override
    public User findById(UUID id) {
        if (id == null){
            throw new InvalidCredentialsException("user id cant be null");
        }
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
