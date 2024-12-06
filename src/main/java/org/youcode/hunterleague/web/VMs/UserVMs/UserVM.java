package org.youcode.hunterleague.web.VMs.UserVMs;


import lombok.*;
import org.youcode.hunterleague.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVM {

    private UUID id;

    private String username;

    private Role role;

    private String firstName;

    private String lastName;

    private String cin;

    private String email;

    private String nationality;

    private LocalDateTime joinDate;

    private LocalDateTime licenseExpirationDate;

}
