package com.mightyblock.users.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto for login and creation of a new user
 */
@ApiModel(description = "Class that represents an user")
@Data
@AllArgsConstructor
public class UserDto {//TODO validar

    @ApiModelProperty(value = "Username from the user to login/add")
    @NotNull(message = "Username cannot be null")
    private String username;
    @ApiModelProperty(value = "Password from the user to login/add")
    @NotNull(message = "Password cannot be null")
    private String password;
}
