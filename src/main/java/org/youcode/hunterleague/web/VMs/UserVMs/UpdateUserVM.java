package org.youcode.hunterleague.web.VMs.UserVMs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserVM {

    private String username;

    private String firstName;

    private String lastName;

    @Email(message = "invalid email format")
    private String email;

    private String nationality;

    @FutureOrPresent(message = "License expiration date cannot be in the past")
    private LocalDateTime licenseExpirationDate;
}
