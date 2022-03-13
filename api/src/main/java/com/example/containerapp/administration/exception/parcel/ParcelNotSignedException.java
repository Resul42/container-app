package com.example.containerapp.administration.exception.parcel;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

public class ParcelNotSignedException extends ResponseStatusException {
    public ParcelNotSignedException(String message) {
        super(CONFLICT, message);
    }

    public static ParcelNotSignedException forId(String id) {
        return new ParcelNotSignedException("Parcel with id: " + id + " was not signed by the Insurance department yet");
    }
}
