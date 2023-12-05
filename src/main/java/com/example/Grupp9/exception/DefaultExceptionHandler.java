package com.example.Grupp9.exception;


import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    //    this is an exception handler for resource not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException e, HttpServletRequest request) {

        ErrorDto errorDto = new ErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    //    this is an exception handler for insufficient authentication
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorDto> handleException(InsufficientAuthenticationException e, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDto);
    }

    //   this is a generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e, HttpServletRequest request) {

        ErrorDto errorDto = new ErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }


    //   this is an exception handler for invalid email
    @ExceptionHandler(ExistsEmailException.class)
    public ResponseEntity<ErrorDto> handleException(ExistsEmailException e, HttpServletRequest request, HttpServletResponse response) {

        ErrorDto errorDto = new ErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }


//
    @ExceptionHandler(HandleMethodArgumentNotValid.class)
    public ResponseEntity<ErrorDto> handleException(HandleMethodArgumentNotValid e, HttpServletRequest request, HttpServletResponse response) {

        ErrorDto errorDto = new ErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(NotFoundException e, HttpServletRequest request, HttpServletResponse response) {

        ErrorDto errorDto = new ErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);

    }  @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidCredentialsException e, HttpServletRequest request, HttpServletResponse response) {

        ErrorDto errorDto = new ErrorDto(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value(), //401
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);

    }
}

