package com.libreria.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> manejarRuntimeException(MethodArgumentNotValidException ex){
		
		
		Map<String,String> errores = new HashMap<>();
		
		// Recorremos todos los errores que encontró Spring
	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errores.put(error.getField(), error.getDefaultMessage());
	    });
		
		return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
				
		
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetalles> manejarRuntimeException(RuntimeException ex) {
	    ErrorDetalles error = new ErrorDetalles(
	        ex.getMessage(), 
	        HttpStatus.NOT_FOUND.value()
	    );
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
