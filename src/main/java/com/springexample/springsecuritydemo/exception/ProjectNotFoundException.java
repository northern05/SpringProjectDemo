package com.springexample.springsecuritydemo.exception;

public class ProjectNotFoundException extends EntityNotFoundException{
    public ProjectNotFoundException() {
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
