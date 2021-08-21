package com.mightyblock.users.service;

import com.mightyblock.users.config.authentication.TokenProvider;
import com.mightyblock.users.dto.TokenDto;
import com.mightyblock.users.dto.UserDto;
import com.mightyblock.users.model.User;
import com.mightyblock.users.model.exceptions.ApiException;
import com.mightyblock.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    /**
     * @param userDto user to login
     * @return TokenDto generated token
     * @throws ApiException
     */
    public TokenDto login(UserDto userDto) throws ApiException {
        User user = repository.findOneByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if(user!=null){
            user.setLastLogin(new Date());
            repository.save(user);
            return tokenProvider.getToken(user.getUsername(), user.getId());
        }
        throw new ApiException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase(), "/users/login");
    }

    /**
     * @param user user to create
     * @return String userId created
     * @throws ApiException when a username already exists or another issue
     */
    public String createUser(UserDto user) throws ApiException {
        if(!repository.findByUsername(user.getUsername()).isEmpty()){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid username", "/users/");
        }
        User userSaved = repository.save(new User(user.getUsername(), user.getPassword()));
        return userSaved.getId();
    }

    /**
     * Deletes an user given an userId
     * @param userId userId to delete
     */
    public void deleteUser(String userId) {
        repository.deleteById(userId);
    }
}
