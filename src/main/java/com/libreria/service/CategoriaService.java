package com.libreria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreria.dto.CategoriaDTO;
import com.libreria.dto.CategoriaResponseDTO;
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
	public List<CategoriaResponseDTO>getAll() {
		
		List<Categoria> cat = categoriaRepository.findAll();
		List<CategoriaResponseDTO> catListDto = new ArrayList<>();
		
		for(int i = 0; i < cat.size(); i++) {
			
			CategoriaResponseDTO catResponse = new CategoriaResponseDTO();
			catResponse.setNombre(cat.get(i).getNombre());
			catResponse.setMensaje("Categoria id: "+cat.get(i).getId());
			catListDto.add(catResponse);
			
			
		}
		
		return catListDto;
		
	}
	
	
	public CategoriaResponseDTO add(CategoriaDTO cat) {
		
		Categoria catBD = new Categoria();
		CategoriaResponseDTO catResponse = new CategoriaResponseDTO();
		
		catBD.setNombre(cat.getNombre());
		catResponse.setNombre(cat.getNombre());
		catResponse.setMensaje("Categoria creada con Exito");
	
		
		categoriaRepository.save(catBD);
		
		
		return catResponse;
	}
	
	
	public CategoriaResponseDTO buscarPorId(Long id) {
		
		Categoria cat = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Id de categoria no encontrado"));
		CategoriaResponseDTO catResponse = new CategoriaResponseDTO();
		
		catResponse.setNombre(cat.getNombre());
		catResponse.setMensaje("Categoria id: "+cat.getId());
		
		return catResponse; 
		
	}
	
	public CategoriaResponseDTO actualizar(CategoriaDTO categoria, Long id) {
		
		
	    
	    Categoria categoriaActualizada = categoriaRepository.findById(id).orElseThrow();
	    CategoriaResponseDTO catResponse = new CategoriaResponseDTO();
	    
	    catResponse.setNombre(categoria.getNombre());
	    catResponse.setMensaje("Categoria Actualizada Exitosamente!");
	    categoriaActualizada.setNombre(categoria.getNombre());
	    
	    categoriaRepository.save(categoriaActualizada);

		
		return catResponse;
		
	}
	
	
	public void eliminar(Long id) {
		
		Categoria cat = categoriaRepository.findById(id).orElseThrow();
	    categoriaRepository.delete(cat);
	    
	   
		
	}


	public Categoria idPrivado(Long categoriaId) {
		Categoria cat = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Id de categoria no encontrado"));
		
		return cat;
	}

}
