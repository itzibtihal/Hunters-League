package org.youcode.hunterleague.repository;


import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.repository.dto.RegisterDTO;

import java.util.Optional;

public interface RegisterRepository {
    User registerUser(RegisterDTO registerDTO);

}