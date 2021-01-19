package com.tsvlad.barber_project.rest.errors.exceptions;

public class DbDataNotFoundException extends RuntimeException {
    public DbDataNotFoundException() {
        super();
    }

    public DbDataNotFoundException(String message) {
        super(message);
    }

    public DbDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbDataNotFoundException(Throwable cause) {
        super(cause);
    }
}
