package com.exercise.accounts.user;

public class InvalidUserNameException extends RuntimeException {
    public InvalidUserNameException(String username) {
        super("invalid username: " + username);
    }
}
