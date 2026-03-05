package com.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.libreria.model.Categoria;
import com.libreria.model.Libro;
import com.libreria.repository.CategoriaRepository;

@Service
public class CategoriaService {

	CategoriaRepository categoriaRepository;
	
	
	
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}


	//obtener todas las categorias
	public List<Categoria>getAll() {
		
		
		return categoriaRepository.findAll();
		
	}
	
	
	public Categoria add(Categoria cat) {
		
		return categoriaRepository.save(cat);
	}
	
	
	public Optional<Categoria> buscarPorId(Long id) {
		
		return categoriaRepository.findById(id);
		
	}
	
	public Categoria actualizar(Categoria categoria, Long id) {
		
		
	    
	    Categoria categoriaActualizada = categoriaRepository.findById(id).orElseThrow();
	    categoriaActualizada.setNombre(categoria.getNombre());
	    categoriaRepository.save(categoriaActualizada);

		
		return categoriaActualizada;
		
	}
	
	
	public void eliminar(Long id) {
		
		Categoria cat = categoriaRepository.findById(id).orElseThrow();
	    categoriaRepository.delete(cat);
	    
	   
		
	}

}
