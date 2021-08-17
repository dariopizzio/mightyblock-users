package com.mightyblock.users.model.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class PropertyNotFoundException extends Exception {

    private HttpStatus status;
    private String message;
}
