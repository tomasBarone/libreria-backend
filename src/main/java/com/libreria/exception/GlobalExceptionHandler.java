package com.libreria.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler { 

    // 1. Manejo de errores de @Valid (Los campos que faltan o están mal)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        
        // Para validaciones se usa habitualmente BAD_REQUEST (400)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // 2. Manejo de errores lógicos (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetalles> manejarRuntimeException(RuntimeException ex) {
        ErrorDetalles error = new ErrorDetalles(
            ex.getMessage(), 
            HttpStatus.INTERNAL_SERVER_ERROR.value() 
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

