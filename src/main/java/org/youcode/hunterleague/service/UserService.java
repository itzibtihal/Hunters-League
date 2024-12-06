package org.youcode.hunterleague.service;

import org.springframework.data.domain.Page;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.service.DTOs.SearchUserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    Page<User> getAllUsersPaginated(int page, int size);
    Boolean deleteUser(UUID id);
    List<User> searchUsers(SearchUserDTO searchUserDTO);
    User updateUser(UUID id,User user);
    User findById(UUID id);

}
