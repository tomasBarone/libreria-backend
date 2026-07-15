package com.libreria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.LibroAnalisisRequestDTO;
import com.libreria.dto.LibroAnalisisResponseDTO;
import com.libreria.model.LibroAnalisis;
import com.libreria.service.LibroAnalisisService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/analisis")
public class LibroAnalisisController {

	final LibroAnalisisService libroAnalisisService;
	
	
	
	
	public LibroAnalisisController(LibroAnalisisService libroAnalisisService) {
		super();
		this.libroAnalisisService = libroAnalisisService;
	}



    @GetMapping("/{id}")
	public ResponseEntity<LibroAnalisisResponseDTO> obtenerInformacion(@PathVariable Long id){
		
		LibroAnalisisResponseDTO analisisResponse = libroAnalisisService.obtener(id);
		
		return ResponseEntity.ok(analisisResponse);
		
	}
    
    
    @PostMapping("/crear")
    @Transactional
    public ResponseEntity<LibroAnalisisResponseDTO> crearInformacion(@Valid @RequestBody LibroAnalisisRequestDTO analisis){
    	
    	LibroAnalisisResponseDTO analisisResponse = libroAnalisisService.crear(analisis);
    	
    	return ResponseEntity.ok(analisisResponse);
    }
	
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<LibroAnalisisResponseDTO> actualizarInformacion(@Valid @RequestBody LibroAnalisisRequestDTO analisis, @PathVariable Long id){
    	
    	
    	
    	LibroAnalisisResponseDTO analisisActualizado = libroAnalisisService.actualizar(analisis, id);
    	
    	return ResponseEntity.ok(analisisActualizado);
    	
    	
    }
	
}
