package com.libreria.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.libreria.model.CorrienteLiteraria;
import com.libreria.model.Libro;
import com.libreria.model.Subgenero;
import com.libreria.repository.CorrienteRepository;
import com.libreria.repository.LibroRepository;
import com.libreria.repository.SubgeneroRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.libreria.dto.CorrienteResponseDTO;
import com.libreria.dto.LibroDTO;
import com.libreria.dto.LibroResponseDTO;
import com.libreria.exception.*;
import com.libreria.mapper.LibroMapper;

@Service
public class LibroService {
	
	
	private final LibroRepository libroRepository;
    private final CorrienteRepository corrienteRepo;
    private final SubgeneroRepository subgeneroRepo;
    private final CloudinaryService cloudinaryService;

	
	private final LibroMapper libroMapper;
	
	
	public LibroService(LibroRepository libroRepository, CorrienteRepository corrienteRepo,
			SubgeneroRepository subgeneroRepo, LibroMapper libroMapper, CloudinaryService cloudinaryService) {
		super();
		this.libroRepository = libroRepository;
		this.corrienteRepo = corrienteRepo;
		this.subgeneroRepo = subgeneroRepo;
		this.libroMapper = libroMapper;
		this.cloudinaryService = cloudinaryService;
	}


	//Crear un libro
	public LibroResponseDTO guardarLibro(LibroDTO dto) {
		
		
		
		/*Libro libro = new Libro();
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
		libroResponse.setCategoriaId(libro.getCategoria().getNombre()); */
		
		// NUEVA FORMA (Mapper)
        Libro libro = libroMapper.toEntity(dto);
        
     // 2. Buscamos las entidades reales en la DB por los IDs que vienen en el DTO
        CorrienteLiteraria corriente = corrienteRepo.findById(dto.getCorrienteId())
            .orElseThrow(() -> new EntityNotFoundException("Corriente no encontrada"));
            
        Subgenero subgenero = subgeneroRepo.findById(dto.getSubgeneroId())
            .orElseThrow(() -> new EntityNotFoundException("Subgenero no encontrado"));

        // 3. Seteamos las relaciones
        libro.setCorriente(corriente);
        libro.setSubgenero(subgenero);
        
        Libro libroGuardado = libroRepository.save(libro);
		
		
		
		return libroMapper.toResponseDTO(libroGuardado);
	}
	
	
	public LibroResponseDTO guardarLibroConImagen(LibroDTO dto, MultipartFile imagen) {
	  
		// A. Reutilizamos la lógica del Mapper y las relaciones transformando a entidad
	    Libro libro = libroMapper.toEntity(dto);
	    
	    CorrienteLiteraria corriente = corrienteRepo.findById(dto.getCorrienteId())
	        .orElseThrow(() -> new EntityNotFoundException("Corriente no encontrada"));
	    Subgenero subgenero = subgeneroRepo.findById(dto.getSubgeneroId())
	        .orElseThrow(() -> new EntityNotFoundException("Subgenero no encontrado"));

	    libro.setCorriente(corriente);
	    libro.setSubgenero(subgenero);

	    // B. Procesamos el archivo físico e inyectamos el nombre en la entidad
	    if (imagen != null && !imagen.isEmpty()) {
	        try {
	         
	        	String urlPublicaCloudinary = cloudinaryService.subirImagen(imagen);
	            libro.setImagenNombre(urlPublicaCloudinary);
	        } catch (IOException e) {
	            throw new RuntimeException("Error al guardar el archivo de la portada", e);
	        }
	    }

	    // C. Guardamos la entidad en la base de datos
	    Libro libroGuardado = libroRepository.save(libro);
	    
	    // D. Mapeamos la respuesta y le cocinamos la URL web final
	    LibroResponseDTO responseDTO = libroMapper.toResponseDTO(libroGuardado);
	    
	    if (libroGuardado.getImagenNombre() != null) {
	       
	        responseDTO.setImagenUrl(libroGuardado.getImagenNombre()); // Para que React dibuje la tapa 3D
	    }
	    
	    return responseDTO;
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
		
		/*
		return paginaLibros.map(libro -> {
	        LibroResponseDTO dto = new LibroResponseDTO();
	        dto.setTitulo(libro.getTitulo());
	        dto.setAutor(libro.getAutor());
	        dto.setAnioPublicacion(libro.getAnioPublicacion());
	        dto.setCategoriaId(libro.getCategoria().getNombre());
	        return dto;
	    }); */
		
		// NUEVA FORMA
        return paginaLibros.map(libroMapper::toResponseDTO);
	}
	
	
	// Buscar por ID
	@Cacheable(value = "libros", key = "#id")
	public LibroResponseDTO obtenerPorId(Long id)  {
		
		
		/*
		if(libroRepository.findById(id).isPresent() == false) {
			
			System.out.println("hola");
			return libroRepository.findById(id).orElseThrow(() -> new GlobalExceptionHandler());
			
		
		}*/
		
		
		Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el libro con id: "+id));
		
		LibroResponseDTO libroResponse = new LibroResponseDTO();
		libroResponse.setId(libro.getId());
		libroResponse.setAnioPublicacion(libro.getAnioPublicacion());
		libroResponse.setAutor(libro.getAutor());
		libroResponse.setTitulo(libro.getTitulo());
		libroResponse.setCorrienteNombre(libro.getCorriente().getNombre());
		libroResponse.setGeneroNombre(libro.getSubgenero().getGenero().getNombre());
		libroResponse.setSubgeneroNombre(libro.getSubgenero().getNombre());
		libroResponse.setIsbn(libro.getIsbn());
		libroResponse.setPrecio(libro.getPrecio());
		libroResponse.setSinopsis(libro.getSinopsis());
		libroResponse.setImagenUrl(libro.getImagenNombre());	
		libroResponse.setEjemplares(libro.getEjemplares());
		System.out.println("Precio: "+libroResponse);
		
		
		return libroResponse;
	}
	
	
	
	public List<LibroResponseDTO> buscarGlobal(String query) {
	    // Le pide al repositorio los libros cuyo título O nombre de autor coincida con la consulta
	    List<Libro> libros = libroRepository.buscarPorTituloOAutor(query);
	    
	    // Mapea las entidades a DTOs de respuesta
	    return libros.stream().map(libroMapper::toResponseDTO).toList();
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
			 lib.setAnioPublicacion(LibrosEncontrados.get(i).getAnioPublicacion());
			 lib.setCorrienteNombre(LibrosEncontrados.get(i).getCorriente().getNombre());
			 lib.setSubgeneroNombre(LibrosEncontrados.get(i).getSubgenero().getNombre());
			 lib.setGeneroNombre(LibrosEncontrados.get(i).getSubgenero().getGenero().getNombre());
			 lib.setId(LibrosEncontrados.get(i).getId());
		  	 lib.setImagenUrl(LibrosEncontrados.get(i).getImagenNombre());
		  	 lib.setIsbn(LibrosEncontrados.get(i).getIsbn());
		  	 lib.setSinopsis(LibrosEncontrados.get(i).getSinopsis());
		  	 lib.setPrecio(LibrosEncontrados.get(i).getPrecio());
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
			lib.setTitulo(libro.get(i).getTitulo());
			lib.setCorrienteNombre(libro.get(i).getCorriente().getNombre());
		    lib.setSubgeneroNombre(libro.get(i).getSubgenero().getNombre());
	  	    lib.setGeneroNombre(libro.get(i).getSubgenero().getGenero().getNombre());
	  	    lib.setId(libro.get(i).getId());
	  	    lib.setImagenUrl(libro.get(i).getImagenNombre());
	  	    lib.setIsbn(libro.get(i).getIsbn());
	  	    lib.setSinopsis(libro.get(i).getSinopsis());
	  	    lib.setPrecio(libro.get(i).getPrecio());
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
			lib.setAutor(libros.get(i).getAutor());
			lib.setCorrienteNombre(libros.get(i).getCorriente().getNombre());
			lib.setSubgeneroNombre(libros.get(i).getSubgenero().getNombre());
			lib.setGeneroNombre(libros.get(i).getSubgenero().getGenero().getNombre());
			lib.setId(libros.get(i).getId());
	  	    lib.setImagenUrl(libros.get(i).getImagenNombre());
	  	    lib.setIsbn(libros.get(i).getIsbn());
	  	    lib.setSinopsis(libros.get(i).getSinopsis());
	  	    lib.setPrecio(libros.get(i).getPrecio());
			libroResponse.add(lib);
			
			
			
			
		}
		
		return libroResponse;
		
	}
	
	
	public LibroResponseDTO actualizar(Long id, LibroDTO nuevoLibro) {
		
		Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("no se encontro el libro con id : " +id));
		
		//LibroResponseDTO libroResponse = new LibroResponseDTO();
		
		
		
		/*libro.setTitulo(nuevoLibro.getTitulo());
		libro.setAutor(nuevoLibro.getAutor());
		libro.setEjemplares(nuevoLibro.getEjemplares());
		libro.setAnioPublicacion(nuevoLibro.getAnioPublicacion());  
		libro.setCategoria(categoria);
		
		libroResponse.setTitulo(nuevoLibro.getTitulo());
		libroResponse.setAutor(nuevoLibro.getAutor());
		libroResponse.setAnioPublicacion(nuevoLibro.getAnioPublicacion());
		libroResponse.setCategoriaId(libro.getCategoria().getNombre());*/
		
	
		
		
		
		// NUEVA FORMA
        libroMapper.updateEntityFromDto(nuevoLibro, libro);
        
        libroRepository.save(libro);
		
		return libroMapper.toResponseDTO(libro); 
		
		
	}
	
	
	@Transactional
	public void eliminar(Long id) {
	    // Verificamos si existe antes de borrar
	    if (!libroRepository.existsById(id)) {
	        throw new RuntimeException("No se puede eliminar: No existe el libro con ID: " + id);
	    }
	    libroRepository.deleteById(id);
	}


	
	/*
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
*/


	
	public Page<LibroResponseDTO> filtrarAvanzado( Integer inicio, Integer fin, Pageable pageable) {
	    return libroRepository.filtrarLibrosPro( inicio, fin, pageable)  
	            .map(l -> {
	                LibroResponseDTO dto = new LibroResponseDTO();
	                dto.setTitulo(l.getTitulo());
	                dto.setAutor(l.getAutor());
	                dto.setAnioPublicacion(l.getAnioPublicacion());
	                
	                return dto;
	            });
	}
	
	
	
	// Cada vez que se reducen ejemplares o se actualiza el libro, borramos la caché de ese libro específico
	@Transactional
	@Caching(evict = {
	        @CacheEvict(value = "libros", key = "#id"),
	        @CacheEvict(value = "catalogoGeneral", allEntries = true) // Limpia toda la lista
	    })
    public void actualizarStock(Long id, Integer cantidad) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        
        libro.reducirEjemplares(cantidad);
        libroRepository.save(libro);
    }



	public List<LibroResponseDTO> buscarPorMovimiento(Long movimientoId) {
	    if (!corrienteRepo.existsById(movimientoId)) {
	        throw new RuntimeException("Error: El movimiento literario no existe");
	    }

	    // 1. Buscamos las entidades reales en la base de datos
	    List<Libro> librosEntidad = libroRepository.findByCorrienteId(movimientoId);

	    // 2. Las mapeamos a DTO para limpiar las referencias circulares y proteger la arquitectura
	    return librosEntidad.stream()
	            .map(libroMapper::toResponseDTO)
	            .toList();
	}


	
	

}
