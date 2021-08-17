package com.mightyblock.users.repository;

import com.mightyblock.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findOneByUsernameAndPassword(String username, String password);

    List<User> findByUsername(String username);

}
