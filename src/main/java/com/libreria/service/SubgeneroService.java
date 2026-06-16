package com.libreria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libreria.dto.SubgeneroResponseDTO;
import com.libreria.mapper.SubgeneroMapper;
import com.libreria.model.Subgenero;
import com.libreria.repository.SubgeneroRepository;

@Service
public class SubgeneroService {
	
	SubgeneroRepository subgeneroRepo;
	SubgeneroMapper subgeneroMapper;
	
	public SubgeneroService(SubgeneroRepository subgeneroRepo, SubgeneroMapper subgeneroMapper) {
		super();
		this.subgeneroRepo = subgeneroRepo;
		this.subgeneroMapper = subgeneroMapper;
	}
	
	
	public List<SubgeneroResponseDTO> listarTodos(){
		
		
		List<Subgenero> subgeneros = subgeneroRepo.findAll();
		
		return subgeneros.stream().map(subgeneroMapper::toResponseDTO).toList();
		
	}

}
