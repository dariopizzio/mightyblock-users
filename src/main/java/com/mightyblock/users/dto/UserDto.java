package com.mightyblock.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto for login and creation of a new user
 */
@Data
@AllArgsConstructor
public class UserDto {//TODO validar

    @NotNull(message = "username cannot be null")
    private String username;
    @NotNull(message = "password cannot be null")
    private String password;
}
