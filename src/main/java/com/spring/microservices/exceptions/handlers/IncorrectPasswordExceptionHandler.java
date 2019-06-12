package com.spring.microservices.exceptions.handlers;

import com.spring.microservices.exceptions.IncorrectPasswordException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IncorrectPasswordExceptionHandler {
    @ExceptionHandler(value = IncorrectPasswordException.class)
    ResponseEntity<Object> exception(IncorrectPasswordException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}