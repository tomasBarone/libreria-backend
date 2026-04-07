package com.libreria.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.LibroDTO;
import com.libreria.exception.GlobalExceptionHandler;
import com.libreria.model.Categoria;
import com.libreria.model.Libro;
import com.libreria.service.CategoriaService;
import com.libreria.service.LibroService;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.libreria.dto.LibroResponseDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Operaciones relacionadas con la gestión de libros")
public class LibroController {
	
    
	private final LibroService libroService;
	private final CategoriaService categoriaService;
	

	@GetMapping("/welcome")	
	public String HomePage() {
		System.out.println("data");
		return "welcome";
	}

	public LibroController(LibroService libroService, CategoriaService categoriaService) {
		super();
		this.libroService = libroService;
		this.categoriaService = categoriaService;
	}
	
	
	
     //POST: Crear un Libro
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LibroResponseDTO> crearLibro(@Valid @RequestBody LibroDTO libroDTO){
		
		if(libroDTO.getIsbn().length() > 13) {
			
			throw new RuntimeException("ISBN invalido");
			
		}
		
		LibroResponseDTO nuevoLibro = libroService.guardarLibro(libroDTO);
		return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
		
	}
	
	
	//GET: Obtener todos
	@GetMapping("/all")
	
	public ResponseEntity<Page<LibroResponseDTO>> listarLibros(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "titulo")Pageable pageable){
		
		return ResponseEntity.ok(libroService.obtenerTodos(pageable));
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LibroResponseDTO> getById(@Valid @PathVariable Long id) throws GlobalExceptionHandler{
		
		LibroResponseDTO libro = libroService.obtenerPorId(id);
		
		return ResponseEntity.ok(libro);
		
	}
	
	
	//Buscar libro por autor o titulo o año de publicacion
	@GetMapping("/buscar")
	public ResponseEntity<List<LibroResponseDTO>> buscar(@RequestParam(required = false) String autor, @RequestParam(required = false) String titulo, @RequestParam(required = false) Integer anioPublicacion) {
	    
		
		
	    if (autor != null) {
	        return ResponseEntity.ok(libroService.obtenerPorAutor(autor));
	    } 
	    if (titulo != null) {
	        return ResponseEntity.ok(libroService.buscarLibrosPorTitulo(titulo));
	    }
	    if(anioPublicacion != null) {
	    	return ResponseEntity.ok(libroService.buscarPorAnio(anioPublicacion));
	    }
	    
	    return ResponseEntity.notFound().build();
	}
	
	
	
	//Actualizar libro
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<LibroResponseDTO> updateBook (@PathVariable Long id, @Valid @RequestBody LibroDTO nuevoLibro){
		
		
		
		System.out.println("hola");
		
		
		LibroResponseDTO libro = libroService.actualizar(id, nuevoLibro);
		
		
		
		return new ResponseEntity<>(libro, HttpStatus.OK);
		//return ResponseEntity.ok(libro);
		
	}
	
	
	
	//Eliminar libro
	@DeleteMapping("/eliminar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Libro> deleteBook(@PathVariable Long id){
		
		 libroService.eliminar(id);
		
		 return ResponseEntity.noContent().build();
		
		
	}
	
	
	
	//Buscar libro por categoria y año
	@GetMapping("/buscar/categoria")
	public ResponseEntity<List<LibroResponseDTO>> listarPorCategoriaYanio(@RequestParam String categoria,@RequestParam int anio){
		
		
		List<LibroResponseDTO> libros = libroService.buscarPorCategoriaYanio(categoria,anio);
		
		return ResponseEntity.ok(libros);
	}
	
	
	
	//Filtrar busqueda para encontrar libros de una categoria determinada en rango de años
	@GetMapping("/filtrar-avanzado")
	public ResponseEntity<Page<LibroResponseDTO>> filtrarLibros(
	        @RequestParam(required = false) String categoria,
	        @RequestParam(required = false) Integer anioInicio,
	        @RequestParam(required = false) Integer anioFin,
	        Pageable pageable) {
	    
	    Page<LibroResponseDTO> libros = libroService.filtrarAvanzado(categoria, anioInicio, anioFin, pageable);
	    return ResponseEntity.ok(libros);
	}

}
