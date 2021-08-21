package com.mightyblock.users.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "users")
//TODO index username unique
public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private Date lastLogin;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
