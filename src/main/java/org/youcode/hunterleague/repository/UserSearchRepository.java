package org.youcode.hunterleague.repository;

import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.service.DTOs.SearchUserDTO;

import java.util.List;

public interface UserSearchRepository {
    List<User> findByCriteria(SearchUserDTO searchUserDTO);

}
