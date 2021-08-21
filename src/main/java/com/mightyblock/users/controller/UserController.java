package com.mightyblock.users.controller;

import com.mightyblock.users.dto.TokenDto;
import com.mightyblock.users.dto.UserDto;
import com.mightyblock.users.model.exceptions.ApiException;
import com.mightyblock.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for every endpoint related with users
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @param user user to login
     * @return TokenDto generated token
     * @throws ApiException
     */
    @PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TokenDto> login(@RequestBody UserDto user) throws ApiException {
        return ResponseEntity.ok(userService.login(user));
    }

    /**
     * @param user user to create
     * @return HttpStatus ok = 200 if the user was created
     * @throws ApiException when a username already exists or another issue
     */
    @PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> createUser(@RequestBody UserDto user) throws ApiException {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    /**
     * @param userId userId to delete
     * @return HttpStatus ok = 200 if the operation was ok
     */
    @DeleteMapping(value = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
