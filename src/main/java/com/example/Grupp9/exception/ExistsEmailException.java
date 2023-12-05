package com.example.Grupp9.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExistsEmailException extends RuntimeException {
    public ExistsEmailException(String message) {
        super(message);
    }

}
