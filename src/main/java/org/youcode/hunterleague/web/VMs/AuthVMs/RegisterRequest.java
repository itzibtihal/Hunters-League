package org.youcode.hunterleague.web.VMs.AuthVMs;

import jakarta.validation.constraints.*;
import lombok.*;
import org.youcode.hunterleague.domain.enums.Role;
import org.youcode.hunterleague.validation.EnumValue;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "username cannot be blank")
    private String username;

    @NotBlank(message = "first name cannot be blank")
    private String firstName;

    @NotBlank(message = "last name cannot be blank")
    private String lastName;

    @Email(message = "invalid email format")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, message = "password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    private String password;

    @Pattern(regexp = "\\d{8}", message = "CIN must be exactly 8 digits")
    private String cin;

    @NotBlank(message = "nationality cannot be blank")
    private String nationality;

    @NotNull(message = "role cannot be null")
    @EnumValue(enumClass = Role.class, message = "invalid role")
    private String role;
}
