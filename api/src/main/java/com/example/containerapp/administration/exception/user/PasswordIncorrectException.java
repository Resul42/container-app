package com.example.containerapp.administration.exception.user;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

public class PasswordIncorrectException extends ResponseStatusException {
    public PasswordIncorrectException() {
        super(UNAUTHORIZED, "The password is incorrect");
    }

}
