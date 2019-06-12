package com.spring.microservices.exceptions;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}