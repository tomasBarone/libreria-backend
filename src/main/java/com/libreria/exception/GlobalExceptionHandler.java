package com.libreria.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler { 

    // 1. Validaciones DTO (@Valid en DTOs) -> 400 BAD REQUEST
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // 2. Errores de Negocio / Validaciones Manuales (e.g. IllegalArgumentException) -> 400 BAD REQUEST
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetalles> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorDetalles error = new ErrorDetalles(
            ex.getMessage(), 
            HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 3. Fallos de Unicidad en la Base de Datos (Constraint Unique Violation) -> 400 BAD REQUEST
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetalles> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErrorDetalles error = new ErrorDetalles(
            "El nombre de usuario o email ya se encuentra registrado.", 
            HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 4. Credenciales de Login Incorrectas -> 401 UNAUTHORIZED
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetalles> handleBadCredentials(BadCredentialsException ex) {
        ErrorDetalles error = new ErrorDetalles(
            "Nombre de usuario, email o contraseña incorrectos.", 
            HttpStatus.UNAUTHORIZED.value()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // 5. Cualquier otro error inesperado en el servidor -> 500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> handleGlobalException(Exception ex) {
        ErrorDetalles error = new ErrorDetalles(
            "Ocurrió un error interno en el servidor: " + ex.getMessage(), 
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
