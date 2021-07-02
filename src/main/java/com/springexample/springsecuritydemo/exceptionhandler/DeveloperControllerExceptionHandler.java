package com.springexample.springsecuritydemo.exceptionhandler;

import com.springexample.springsecuritydemo.exception.DeveloperNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class DeveloperControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionMessageGetter exceptionMessageGetter;

    @Autowired
    public DeveloperControllerExceptionHandler(ExceptionMessageGetter exceptionMessageGetter) {
        this.exceptionMessageGetter = exceptionMessageGetter;
    }

    @ExceptionHandler(value = {DeveloperNotFoundException.class})
    protected ResponseEntity<Object> handlerDeveloperNotFound(DeveloperNotFoundException exception, WebRequest request){
        String error = exceptionMessageGetter.getMessage(DeveloperNotFoundException.class);
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, exception.getLocalizedMessage(), error);
        return  new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
