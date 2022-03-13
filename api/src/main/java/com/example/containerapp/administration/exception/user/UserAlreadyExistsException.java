package com.example.containerapp.administration.exception.user;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(String message) {
        super(CONFLICT, message);
    }

    public static UserAlreadyExistsException forEmail(String email) {
        return new UserAlreadyExistsException("The user: " + email + " already exists");
    }

}

