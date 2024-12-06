package org.youcode.hunterleague.service.dto;

import lombok.Data;
import org.youcode.hunterleague.domain.enums.Role;

@Data
public class UserDTO {
    private String username;
    private Role role;

}
