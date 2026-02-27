package com.libreria.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.libreria.model.Libro;
import com.libreria.repository.LibroRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
public class LibroService {
	
	
	private final LibroRepository libroRepository;
	
	public LibroService(LibroRepository libroRepository) {
		this.libroRepository = libroRepository;
	}
	
	
	
	//Crear un libro
	public Libro guardarLibro(Libro libro) {
		
		return libroRepository.save(libro);
		
	}
	
	
	// Listar todos
	public List<Libro> obtenerTodos(){
		return libroRepository.findAll();
	}
	
	
	// Buscar por ID
	public Libro obtenerPorId(Long id) {
		
		
		return libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
		
		
	}
	
	
	public List<Libro> obtenerPorAutor(String autor) {
		
		//opciones por si quiero manipular la lista
		 //declararla manualmente
		
		//List<Libro> LibrosEncontrados = libroRepository.findByAutor(autor);
		 
		 /*
		 *if (librosEncontrados.isEmpty()) {
         Podrías hacer algo especial aquí
                        }
		 */
		
		
		
		return libroRepository.findByAutor(autor);
		
	}
	
	
	public List<Libro> buscarLibrosPorTitulo(String palabraClave) {
	    return libroRepository.findByTituloContainingIgnoreCase(palabraClave);
	}

}
