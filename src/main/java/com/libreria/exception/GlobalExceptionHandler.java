package com.libreria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetalles> manejarRuntimeException(RuntimeException ex){
		
		ErrorDetalles error = new ErrorDetalles(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
				
		
	}

}
