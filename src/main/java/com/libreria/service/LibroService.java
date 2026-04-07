package com.libreria.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.libreria.model.Categoria;
import com.libreria.model.Libro;
import com.libreria.repository.CategoriaRepository;
import com.libreria.repository.LibroRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.libreria.dto.CategoriaDTO;
import com.libreria.dto.CategoriaResponseDTO;
import com.libreria.dto.LibroDTO;
import com.libreria.dto.LibroResponseDTO;
import com.libreria.exception.*;

@Service
public class LibroService {
	
	
	private final LibroRepository libroRepository;

	private CategoriaService categoriaService;
	
	

	
	public LibroService(LibroRepository libroRepository, CategoriaService categoriaService) {
		super();
		this.libroRepository = libroRepository;
		this.categoriaService = categoriaService;
	}


	//Crear un libro
	public LibroResponseDTO guardarLibro(LibroDTO dto) {
		
		Categoria categoria = categoriaService.idPrivado(dto.getCategoriaId());
		
		Libro libro = new Libro();
		libro.setTitulo(dto.getTitulo());
		libro.setAutor(dto.getAutor());
		libro.setAnioPublicacion(dto.getAnioPublicacion());
		libro.setEjemplares(dto.getEjemplares());
		libro.setCategoria(categoria);
		libro.setIsbn(dto.getIsbn());
		
		libroRepository.save(libro);
		
		LibroResponseDTO libroResponse = new LibroResponseDTO();
		libroResponse.setTitulo(dto.getTitulo());
		libroResponse.setAutor(dto.getAutor());
		libroResponse.setAnioPublicacion(dto.getAnioPublicacion());
		libroResponse.setCategoriaId(libro.getCategoria().getNombre());
		
		
		
		return libroResponse;
	}
	
	
	// Listar todos
	public Page<LibroResponseDTO> obtenerTodos(Pageable pageable){
		
		Page<Libro> paginaLibros = libroRepository.findAll(pageable);
		//List<LibroResponseDTO> libroResponse = new ArrayList<>();
		
		/*
		for(int i = 0; i < libro.size(); i++) {
			
		
		 LibroResponseDTO lib = new LibroResponseDTO();
		 lib.setAnioPublicacion(libro.get(i).getAnioPublicacion());
		 lib.setTitulo(libro.get(i).getTitulo());
		 lib.setAutor(libro.get(i).getAutor());
		 lib.setCategoriaId(libro.get(i).getCategoria().getNombre());
		 libroResponse.add(lib);
			
			
		}
		*/
		
		return paginaLibros.map(libro -> {
	        LibroResponseDTO dto = new LibroResponseDTO();
	        dto.setTitulo(libro.getTitulo());
	        dto.setAutor(libro.getAutor());
	        dto.setAnioPublicacion(libro.getAnioPublicacion());
	        dto.setCategoriaId(libro.getCategoria().getNombre());
	        return dto;
	    });
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
	
	
	public LibroResponseDTO actualizar(Long id, LibroDTO nuevoLibro) {
		
		Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("no se encontro el libro con id : " +id));
		Categoria categoria = categoriaService.idPrivado(nuevoLibro.getCategoriaId());
		LibroResponseDTO libroResponse = new LibroResponseDTO();
		
		
		
		libro.setTitulo(nuevoLibro.getTitulo());
		libro.setAutor(nuevoLibro.getAutor());
		libro.setEjemplares(nuevoLibro.getEjemplares());
		libro.setAnioPublicacion(nuevoLibro.getAnioPublicacion());  
		libro.setCategoria(categoria);
		
		libroResponse.setTitulo(nuevoLibro.getTitulo());
		libroResponse.setAutor(nuevoLibro.getAutor());
		libroResponse.setAnioPublicacion(nuevoLibro.getAnioPublicacion());
		libroResponse.setCategoriaId(libro.getCategoria().getNombre());
		
	
		
		
		
		
		libroRepository.save(libro);
		
		
		return libroResponse;
		
	}
	
	
	@Transactional
	public void eliminar(Long id) {
	    // Verificamos si existe antes de borrar
	    if (!libroRepository.existsById(id)) {
	        throw new RuntimeException("No se puede eliminar: No existe el libro con ID: " + id);
	    }
	    libroRepository.deleteById(id);
	}


	
	
	public List<LibroResponseDTO> buscarPorCategoriaYanio(String categoria, int anio) {
	
		//List<CategoriaResponseDTO> categorias = categoriaService.getAll();
		List<Libro> libros = libroRepository.findByCategoriaNombreAndAnioPublicacionLessThan(categoria,anio);
		List<LibroResponseDTO> librosResponse = new ArrayList<>();
		
		
		for(Libro l : libros ) {
			
			LibroResponseDTO libDTO = new LibroResponseDTO();
			libDTO.setTitulo(l.getTitulo());
			libDTO.setAnioPublicacion(l.getAnioPublicacion());
			libDTO.setAutor(l.getAutor());
			libDTO.setCategoriaId(categoria);
			librosResponse.add(libDTO);
			
		}
		
	    
		return librosResponse;
	}



	
	public Page<LibroResponseDTO> filtrarAvanzado(String cat, Integer inicio, Integer fin, Pageable pageable) {
	    return libroRepository.filtrarLibrosPro(cat, inicio, fin, pageable)  
	            .map(l -> {
	                LibroResponseDTO dto = new LibroResponseDTO();
	                dto.setTitulo(l.getTitulo());
	                dto.setAutor(l.getAutor());
	                dto.setAnioPublicacion(l.getAnioPublicacion());
	                dto.setCategoriaId(l.getCategoria().getNombre());
	                return dto;
	            });
	}
	
	

}
