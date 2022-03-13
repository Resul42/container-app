package com.example.containerapp.administration.exception.parcel;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

public class DepartmentAuthorizationException extends ResponseStatusException {
    public DepartmentAuthorizationException(String message) {
        super(UNAUTHORIZED, message);
    }

    public static DepartmentAuthorizationException forDepartment(String id, String userDepartment) {
        return new DepartmentAuthorizationException("The parcel with id: " + id + " does not belong under the " + userDepartment + " which the user is in.");
    }

}
