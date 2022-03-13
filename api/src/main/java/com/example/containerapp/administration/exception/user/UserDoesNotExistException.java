package com.example.containerapp.administration.exception.user;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

public class UserDoesNotExistException extends ResponseStatusException {
    public static final String DOES_NOT_EXIST = " does not exist";
    public static final String THE_USER_WITH_UUID = "The user with uuid: ";
    public UserDoesNotExistException(String message) {
        super(FORBIDDEN, message);
    }

    public static UserDoesNotExistException forEmail(String email) {
        return new UserDoesNotExistException("The user: " + email + DOES_NOT_EXIST);
    }

    public static UserDoesNotExistException forUuid(String uuid) {
        return new UserDoesNotExistException(THE_USER_WITH_UUID + uuid + DOES_NOT_EXIST);
    }

    public static UserDoesNotExistException forUuidAndEmail(String uuid, String email) {
        return new UserDoesNotExistException(THE_USER_WITH_UUID + uuid + " and email: " + email + DOES_NOT_EXIST);
    }

}
