package com.exercise.accounts.exception;

public class InvalidUserNameException extends RuntimeException {
    public InvalidUserNameException(String username) {
        super("invalid username: " + username);
    }
}
