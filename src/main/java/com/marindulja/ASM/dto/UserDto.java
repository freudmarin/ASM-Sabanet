package com.marindulja.ASM.dto;

import com.marindulja.ASM.model.users.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    private String userType;

    private Role role;
}
