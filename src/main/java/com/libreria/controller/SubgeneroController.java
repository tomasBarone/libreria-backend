package com.libreria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.SubgeneroResponseDTO;
import com.libreria.model.Subgenero;
import com.libreria.service.SubgeneroService;

@RestController
@RequestMapping("/api/subgenero")
public class SubgeneroController {
	
	SubgeneroService subgeneroService;
	
	
	
	public SubgeneroController(SubgeneroService subgeneroService) {
		super();
		this.subgeneroService = subgeneroService;
	}



	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<SubgeneroResponseDTO>> listarTodos(){
		
		List<SubgeneroResponseDTO> subgeneros = subgeneroService.listarTodos();
		
		return ResponseEntity.ok(subgeneros);
		
	}
	

}
