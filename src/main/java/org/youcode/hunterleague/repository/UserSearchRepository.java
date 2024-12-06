package org.youcode.hunterleague.repository;

import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.service.DTOs.SearchUserDTO;

import java.util.List;

@Repository
public interface UserSearchRepository {
    List<User> findByCriteria(SearchUserDTO searchUserDTO);

}
