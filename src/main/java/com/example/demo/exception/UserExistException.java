package com.example.demo.exception;


public class UserExistException extends RuntimeException {
    public UserExistException(String username) {
        super("User with username '" + username + "' already exists.");
    }
}
