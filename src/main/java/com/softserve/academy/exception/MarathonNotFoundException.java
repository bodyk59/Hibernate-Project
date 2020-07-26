package com.softserve.academy.exception;

public class MarathonNotFoundException extends RuntimeException {
    public MarathonNotFoundException(long id) {
        super("Could not find marathon with id: " + id);
    }
}
