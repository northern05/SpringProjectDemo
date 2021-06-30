package com.springexample.springsecuritydemo.exception;

public class DepartmentNotFoundException extends EntityNotFoundException {
    public DepartmentNotFoundException() {}

    public DepartmentNotFoundException(String message) {
        super(message);
    }

    public DepartmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
