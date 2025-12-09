package com.agenda.contatos.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    // ================================
    // 404 - Resource Not Found
    // ================================
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();
        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    // ================================
    // 422 - Validation Error (@Valid)
    // ================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError error = new ValidationError();
        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Validation Error");
        error.setMessage("Campos inválidos na requisição.");
        error.setPath(request.getRequestURI());

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(e -> error.addError(e.getField(), e.getDefaultMessage()));

        return ResponseEntity.status(status).body(error);
    }

    // ================================
    // 400 - Database Error
    // ================================
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseError(
            DatabaseException exception,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database error");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
