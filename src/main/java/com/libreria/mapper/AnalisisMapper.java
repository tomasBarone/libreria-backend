package com.libreria.mapper;

import org.springframework.stereotype.Component;

import com.libreria.dto.LibroAnalisisResponseDTO;
import com.libreria.model.LibroAnalisis;

@Component
public class AnalisisMapper {

	
	public LibroAnalisisResponseDTO toResponseDTO(LibroAnalisis libroAnalisis) {
		
		LibroAnalisisResponseDTO dto = new LibroAnalisisResponseDTO();
		dto.setEjePsicologico(libroAnalisis.getEjePsicologico());
		dto.setIntroduccionTeorica(libroAnalisis.getIntroduccionTeorica());
		dto.setMapaSensaciones(libroAnalisis.getMapaSensaciones());
		dto.setSustratoFilosofico(libroAnalisis.getSustratoFilosofico());
		dto.setLibro(libroAnalisis.getLibro().getTitulo());
		dto.setId(libroAnalisis.getId());
		
		return dto;
		
	}
	
	
	
}
