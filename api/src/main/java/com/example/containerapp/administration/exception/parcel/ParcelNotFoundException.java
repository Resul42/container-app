package com.example.containerapp.administration.exception.parcel;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

public class ParcelNotFoundException extends ResponseStatusException {
    public ParcelNotFoundException(String message) {
        super(NOT_FOUND, message);
    }

    public static ParcelNotFoundException forId(String id) {
        return new ParcelNotFoundException("No parcel with id: " + id + " was found");
    }

}
