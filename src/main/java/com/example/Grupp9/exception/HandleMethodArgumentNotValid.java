package com.example.Grupp9.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HandleMethodArgumentNotValid extends RuntimeException {
    public HandleMethodArgumentNotValid(String message) {
        super(String.valueOf(message));
    }
}
