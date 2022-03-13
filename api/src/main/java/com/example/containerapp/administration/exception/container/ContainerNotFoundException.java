package com.example.containerapp.administration.exception.container;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

public class ContainerNotFoundException extends ResponseStatusException {
    public ContainerNotFoundException(String message) {
        super(NOT_FOUND, message);
    }

    public static ContainerNotFoundException forId(String id) {
        return new ContainerNotFoundException("No container with id: " + id + " was found");
    }

}
