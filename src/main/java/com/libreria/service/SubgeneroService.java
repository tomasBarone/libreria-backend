package com.libreria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libreria.dto.SubgeneroRequestDTO;
import com.libreria.dto.SubgeneroResponseDTO;
import com.libreria.mapper.SubgeneroMapper;
import com.libreria.model.GeneroLiterario;
import com.libreria.model.Subgenero;
import com.libreria.repository.GeneroRepository;
import com.libreria.repository.SubgeneroRepository;

@Service
public class SubgeneroService {
	
	SubgeneroRepository subgeneroRepo;
	SubgeneroMapper subgeneroMapper;
	GeneroRepository generoLiterarioRepo;
	
	public SubgeneroService(SubgeneroRepository subgeneroRepo, SubgeneroMapper subgeneroMapper, GeneroRepository generoRepo) {
		super();
		this.subgeneroRepo = subgeneroRepo;
		this.subgeneroMapper = subgeneroMapper;
		this.generoLiterarioRepo = generoRepo;
	}
	
	
	public List<SubgeneroResponseDTO> listarTodos(){
		
		
		List<Subgenero> subgeneros = subgeneroRepo.findAll();
		
		return subgeneros.stream().map(subgeneroMapper::toResponseDTO).toList();
		
	}


	public SubgeneroResponseDTO actualizarRegistro(Long id, SubgeneroRequestDTO subgenero) {
		
	
		Subgenero subgeneroEntidad = subgeneroRepo.findById(id).orElseThrow(() -> new RuntimeException());
	    GeneroLiterario genero = generoLiterarioRepo.findById(subgeneroEntidad.getGenero().getId()).orElseThrow(() -> new RuntimeException());
	    SubgeneroResponseDTO subgeneroResponse = new SubgeneroResponseDTO();
	    subgeneroEntidad.setGenero(genero);
	    subgeneroEntidad.setDescripcion(subgenero.getDescripcion());
	    subgeneroEntidad.setNombre(subgenero.getNombre());
		
		subgeneroRepo.save(subgeneroEntidad);
		
		subgeneroResponse.setId(subgeneroEntidad.getId());
		subgeneroResponse.setGeneroNombre(genero.getNombre());
		subgeneroResponse.setNombre(subgeneroEntidad.getNombre());
		subgeneroResponse.setDescripcion(subgeneroEntidad.getDescripcion());
		
		
		return subgeneroResponse;
	}

}
