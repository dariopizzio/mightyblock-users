package com.mightyblock.users.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {//TODO validar

    @NotNull(message = "username cannot be null")
    private String username;
    @NotNull(message = "password cannot be null")
    private String password;
}
