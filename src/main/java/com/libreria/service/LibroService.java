package com.libreria.service;


import java.util.ArrayList;
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
import com.libreria.dto.LibroResponseDTO;
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
		
		libro.setIsbn(dto.getIsbn());
		
		return libroRepository.save(libro);
	}
	
	
	// Listar todos
	public List<LibroResponseDTO> obtenerTodos(){
		
		List<Libro> libro = libroRepository.findAll();
		List<LibroResponseDTO> libroResponse = new ArrayList<>();
		
		for(int i = 0; i < libro.size(); i++) {
			
		
		 LibroResponseDTO lib = new LibroResponseDTO();
		 lib.setAnioPublicacion(libro.get(i).getAnioPublicacion());
		 lib.setTitulo(libro.get(i).getTitulo());
		 lib.setAutor(libro.get(i).getAutor());
		 lib.setCategoriaId(libro.get(i).getCategoria().getNombre());
		 libroResponse.add(lib);
			
			
		}
		
		
		return libroResponse;
	}
	
	
	// Buscar por ID
	public LibroResponseDTO obtenerPorId(Long id)  {
		
		
		/*
		if(libroRepository.findById(id).isPresent() == false) {
			
			System.out.println("hola");
			return libroRepository.findById(id).orElseThrow(() -> new GlobalExceptionHandler());
			
		
		}*/
		
		
		Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el libro con id: "+id));
		
		LibroResponseDTO libroResponse = new LibroResponseDTO();
		libroResponse.setAnioPublicacion(libro.getAnioPublicacion());
		libroResponse.setAutor(libro.getAutor());
		libroResponse.setCategoriaId(libro.getCategoria().getNombre());
		libroResponse.setTitulo(libro.getTitulo());
		
		
		return libroResponse;
	}
	
	
	
	
	
	
	public List<LibroResponseDTO> obtenerPorAutor(String autor) {
		
		//opciones por si quiero manipular la lista
		 //declararla manualmente
		
		 List<Libro> LibrosEncontrados = libroRepository.findByAutorContainingIgnoreCase(autor);
		 List<LibroResponseDTO> libroResponse = new ArrayList<>();
		 
		 for(int i = 0; i < LibrosEncontrados.size(); i++) {
			 
			 LibroResponseDTO lib = new LibroResponseDTO("", "");
			 lib.setAutor(LibrosEncontrados.get(i).getAutor());
			 lib.setTitulo(LibrosEncontrados.get(i).getTitulo());
			 libroResponse.add(lib);
			 
		 }
		 
		 
		return libroResponse;
		
	}
	
	
	public List<LibroResponseDTO> buscarLibrosPorTitulo(String palabraClave) {
		
		List<Libro> libro = libroRepository.findByTituloContainingIgnoreCase(palabraClave);
		List<LibroResponseDTO> libroResponse = new ArrayList<>();
		
		for(int i = 0; i < libro.size(); i++) {
			
			LibroResponseDTO lib = new LibroResponseDTO();
			lib.setAnioPublicacion(libro.get(i).getAnioPublicacion());
			lib.setAutor(libro.get(i).getAutor());
			lib.setCategoriaId(libro.get(i).getCategoria().getNombre());
			lib.setTitulo(libro.get(i).getTitulo());
			libroResponse.add(lib);
			
			
			
		}
		
		
		
	    return libroResponse;
	}
	
	
	
	public List<LibroResponseDTO> buscarPorAnio(Integer anio){
		
		
		List<Libro> libros = libroRepository.findByanioPublicacion(anio);
		List<LibroResponseDTO> libroResponse = new ArrayList<>();
		
		
		for(int i = 0; i < libros.size(); i++ ) {
			
			
			LibroResponseDTO lib = new LibroResponseDTO();
			lib.setAnioPublicacion(libros.get(i).getAnioPublicacion());
			lib.setTitulo(libros.get(i).getTitulo());
			libroResponse.add(lib);
			
			
			
			
		}
		
		return libroResponse;
		
	}
	
	
	public Libro actualizar(Long id, LibroDTO nuevoLibro) {
		
		Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("no se encontro el libro con id : " +id));
		Categoria categoria = categoriaService.buscarPorId(nuevoLibro.getCategoriaId());
		
		
		
		
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
