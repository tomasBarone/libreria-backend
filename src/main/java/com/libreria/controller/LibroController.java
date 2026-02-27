package com.libreria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.model.Libro;
import com.libreria.service.LibroService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/libros")
public class LibroController {
	
	

	private final LibroService libroService;
	
	@GetMapping("/welcome")	
	public String HomePage() {
		System.out.println("data");
		return "welcome";
	}

	public LibroController(LibroService libroService) {
		super();
		this.libroService = libroService;
	}
	
	
	
     //POST: Crear un Libro
	@PostMapping
	public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro){
		
		Libro nuevoLibro = libroService.guardarLibro(libro);
		return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
		
	}
	
	
	//GET: Obtener todos
	@GetMapping("/all")
	public ResponseEntity<List<Libro>> listarLibros(){
		
		return ResponseEntity.ok(libroService.obtenerTodos());
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Libro> getById(@PathVariable Long id){
		
		Libro libro = libroService.obtenerPorId(id);
		
		return ResponseEntity.ok(libro);
		
	}
	
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Libro>> buscar(@RequestParam(required = false) String autor, @RequestParam(required = false) String titulo) {
	    
	    if (autor != null) {
	        return ResponseEntity.ok(libroService.obtenerPorAutor(autor));
	    } 
	    if (titulo != null) {
	        return ResponseEntity.ok(libroService.buscarLibrosPorTitulo(titulo));
	    }
	    
	    return ResponseEntity.ok(libroService.obtenerTodos()); // Si no manda nada, trae todo
	}

}
