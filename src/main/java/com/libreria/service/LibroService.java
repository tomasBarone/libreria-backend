package com.libreria.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreria.model.Categoria;
import com.libreria.model.Libro;
import com.libreria.repository.CategoriaRepository;
import com.libreria.repository.LibroRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.libreria.dto.LibroDTO;
import com.libreria.exception.*;

@Service
public class LibroService {
	
	
	private final LibroRepository libroRepository;

	private  CategoriaService categoriaService;
	
	

	
	public LibroService(LibroRepository libroRepository, CategoriaService categoriaService) {
		super();
		this.libroRepository = libroRepository;
		this.categoriaService = categoriaService;
	}


	//Crear un libro
	public Libro guardarLibro(LibroDTO dto) {
		
		Categoria categoria = categoriaService.buscarPorId(dto.getCategoriaId());
		
		Libro libro = new Libro();
		libro.setTitulo(dto.getTitulo());
		libro.setAutor(dto.getAutor());
		libro.setAnioPublicacion(dto.getAnioPublicacion());
		libro.setEjemplares(dto.getEjemplares());
		libro.setCategoria(categoria);
		
		return libroRepository.save(libro);
	}
	
	
	// Listar todos
	public List<Libro> obtenerTodos(){
		return libroRepository.findAll();
	}
	
	
	// Buscar por ID
	public Libro obtenerPorId(Long id)  {
		
		
		/*
		if(libroRepository.findById(id).isPresent() == false) {
			
			System.out.println("hola");
			return libroRepository.findById(id).orElseThrow(() -> new GlobalExceptionHandler());
			
		
		}*/
		
		return libroRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el libro con ID: "+id));
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
	
	
	
	public List<Libro> buscarPorAnio(Integer anio){
		
		return libroRepository.findByanioPublicacion(anio);
		
	}
	
	
	public Libro actualizar(Long id, Libro nuevoLibro) {
		
		Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("no se encontro el libro con id : " +id));
		Categoria categoria = categoriaService.buscarPorId(nuevoLibro.getCategoria().getId());
		
		
		libro.setTitulo(nuevoLibro.getTitulo());
		libro.setAutor(nuevoLibro.getAutor());
		libro.setEjemplares(nuevoLibro.getEjemplares());
		libro.setAnioPublicacion(nuevoLibro.getAnioPublicacion());  
		libro.setCategoria(categoria);
		
		
		
		
		libroRepository.save(libro);
		
		
		return libro;
		
	}
	
	
	@Transactional
	public void eliminar(Long id) {
	    // Verificamos si existe antes de borrar
	    if (!libroRepository.existsById(id)) {
	        throw new RuntimeException("No se puede eliminar: No existe el libro con ID: " + id);
	    }
	    libroRepository.deleteById(id);
	}



	
	
	
	

}
