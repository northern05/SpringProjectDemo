package com.springexample.springsecuritydemo.exception;

public class DeveloperNotFoundException extends EntityNotFoundException{
    public DeveloperNotFoundException() {
    }

    public DeveloperNotFoundException(String message) {
        super(message);
    }

    public DeveloperNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
