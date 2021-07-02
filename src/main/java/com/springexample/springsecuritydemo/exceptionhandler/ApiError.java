package com.springexample.springsecuritydemo.exceptionhandler;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private String hint;
    private List<String> errors;

    private Map<String, Object> additionalProperties = new HashMap<>();

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        this(status, message, Collections.singletonList(error));
    }

    public ApiError(HttpStatus status, String message, String error, String hint) {
        this(status, message, error);
        this.hint = hint;
    }

    public ApiError(HttpStatus status, String message, String error, String hint, Map<String, Object> additionalProperty) {
        this.status = status;
        this.message = message;
        this.hint = hint;
        this.errors = Collections.singletonList(error);
        this.additionalProperties = additionalProperty;
    }


}
