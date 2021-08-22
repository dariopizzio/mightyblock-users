package com.mightyblock.users.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Login response for httpStatus = ok
 */
@ApiModel(description = "Response for a successfully login")
@Data
@AllArgsConstructor
public class TokenDto {

    @ApiModelProperty(value = "Token for the user logged in the application")
    private String token;
    @ApiModelProperty(value = "Expire time for the token in milliseconds")
    private Long expireTime;
}
