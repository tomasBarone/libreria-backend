package com.libreria.service;

import java.util.List;

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
	
	

}
