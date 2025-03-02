package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandle {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage todoException() {
        return new ErrorMessage(400, "Can not import null value");
    }
    @ExceptionHandler(UserExistException.class)
    public ErrorMessage handleUserExistException(UserExistException ex) {
        return new ErrorMessage(400, ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleCustomException(Exception e) {
        return new ErrorMessage(400, e.getMessage());
    }
}
