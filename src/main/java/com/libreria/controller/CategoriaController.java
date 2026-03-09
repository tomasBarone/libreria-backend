package com.libreria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.CategoriaDTO;
import com.libreria.dto.CategoriaResponseDTO;
import com.libreria.model.Categoria;
import com.libreria.model.Libro;
import com.libreria.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {
	
	CategoriaService categoriaService;
	
	

	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}


	@GetMapping("/todas")
	public ResponseEntity<List<CategoriaResponseDTO>> obtenerTodas() {
		
		List<CategoriaResponseDTO> cat =  categoriaService.getAll();
		
		return ResponseEntity.ok(cat);
	}
	
	
	@PostMapping("/agregar")
	public ResponseEntity<CategoriaResponseDTO> agregar(@Valid @RequestBody CategoriaDTO cat){
		
		CategoriaResponseDTO cat1 = categoriaService.add(cat);
		
		return ResponseEntity.ok(cat1);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> buscarPorId(@Valid @PathVariable Long id){
		
		  CategoriaResponseDTO cat = categoriaService.buscarPorId(id);
		
		return ResponseEntity.ok(cat);
		
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<CategoriaResponseDTO> actualizarCategoria( @Valid @RequestBody CategoriaDTO cat, @PathVariable Long id){
		
		
		CategoriaResponseDTO categoriaActualizada = categoriaService.actualizar(cat,id);
		
		
		
		return ResponseEntity.ok(categoriaActualizada);
	}
	
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<Categoria> eliminarCategoria (@Valid @PathVariable Long id){
		
		 categoriaService.eliminar(id);
		
		return ResponseEntity.ok(null);
		
	}

}
