package org.youcode.hunterleague.repository;


import org.youcode.hunterleague.domain.User;

import java.util.Optional;

public interface RegisterRepository {
    User registerUser(User user);

}