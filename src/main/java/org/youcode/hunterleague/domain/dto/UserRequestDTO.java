package org.youcode.hunterleague.domain.dto;

import lombok.Data;
import org.youcode.hunterleague.domain.enums.Role;

import java.time.LocalDate;

@Data
public class UserRequestDTO {

    private String membershipNumber;
    private String firstName;
    private String lastName;
    private String nationality;
    private String cin;
    private String username;
    private String email;
    private String password;
    private LocalDate joinDate;
    private LocalDate licenseExpirationDate;
    private Role role;

}

// to add this in vm package org.youcode.hunterleague.dto;
//
//import lombok.Data;
//import org.youcode.hunterleague.domain.enums.Role;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Data
//public class UserResponseDTO {
//
//    private UUID id;
//    private String membershipNumber;
//    private String firstName;
//    private String lastName;
//    private String nationality;
//    private String username;
//    private String email;
//    private LocalDate joinDate;
//    private LocalDate licenseExpirationDate;
//    private LocalDateTime createdAt;
//    private Role role;
//}