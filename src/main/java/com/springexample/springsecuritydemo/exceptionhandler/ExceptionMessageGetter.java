package com.springexample.springsecuritydemo.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ExceptionMessageGetter {
    private final MessageSource messageSource;

    @Autowired
    public ExceptionMessageGetter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(Class<?> clazz) {
        String className = getSimpleClassName(clazz);
        return messageSource.getMessage(getExceptionMessage(className) + ".message",
                null, Locale.getDefault());
    }

    private String getExceptionMessage(String className) {
        return Stream.of(className.split("(?=[A-Z])"))
                .map(String::toLowerCase)
                .collect(Collectors.joining("."));
    }

    private  String getHint (Class<?> clazz){
        String className = getSimpleClassName(clazz);
        return messageSource.getMessage(getExceptionMessage(className) + ".hint", null, Locale.getDefault());
    }

    private String getSimpleClassName(Class<?> clazz) {
        return clazz.getSimpleName();
    }
}
