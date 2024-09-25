package com.exercise.accounts.exception;

public class ServiceUnavailableErrorException extends RuntimeException {
    public ServiceUnavailableErrorException(String message) {
            super(message);
    }
}

