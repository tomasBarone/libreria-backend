package com.libreria.service;

import org.springframework.stereotype.Service;

import com.libreria.dto.LibroAnalisisRequestDTO;
import com.libreria.dto.LibroAnalisisResponseDTO;
import com.libreria.model.Libro;
import com.libreria.model.LibroAnalisis;
import com.libreria.repository.LibroAnalisisRepository;
import com.libreria.repository.LibroRepository;

import jakarta.validation.Valid;

@Service
public class LibroAnalisisService {
	
	final LibroAnalisisRepository libroAnalisisRepo;
	final LibroRepository libroRepo;
	
	

	public LibroAnalisisService(LibroAnalisisRepository libroAnalisisRepo,LibroRepository libroRepo) {
		super();
		this.libroAnalisisRepo = libroAnalisisRepo;
		this.libroRepo = libroRepo;
	}



	public LibroAnalisisResponseDTO obtener(Long id) {
		
		LibroAnalisis analisisResponse = libroAnalisisRepo.findById(id).orElseThrow( ()-> new RuntimeException("Analisis no encontrado"));
		
		LibroAnalisisResponseDTO analisisDTO = new LibroAnalisisResponseDTO();
		
		analisisDTO.setId(analisisResponse.getId());
		analisisDTO.setLibro(analisisResponse.getLibro().getTitulo());
		analisisDTO.setIntroduccionTeorica(analisisResponse.getIntroduccionTeorica());
		analisisDTO.setEjePsicologico(analisisResponse.getEjePsicologico());
		analisisDTO.setSustratoFilosofico(analisisResponse.getSustratoFilosofico());
		analisisDTO.setMapaSensaciones(analisisResponse.getMapaSensaciones());
		
		
		
		return analisisDTO;
	}



	public LibroAnalisisResponseDTO crear(@Valid LibroAnalisisRequestDTO analisis) {
		
		Libro libro = libroRepo.findById(analisis.getId()).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
		LibroAnalisisResponseDTO analisisResponseDTO = new LibroAnalisisResponseDTO();
		
		LibroAnalisis analisisEntity = new LibroAnalisis();
		
		analisisEntity.setLibro(libro);
		analisisEntity.setIntroduccionTeorica(analisis.getIntroduccionTeorica());
		analisisEntity.setEjePsicologico(analisis.getEjePsicologico());
		analisisEntity.setMapaSensaciones(analisis.getMapaSensaciones());
		analisisEntity.setSustratoFilosofico(analisis.getSustratoFilosofico());
		libroAnalisisRepo.save(analisisEntity);
		
		
		analisisResponseDTO.setLibro(analisisEntity.getLibro().getTitulo());
		analisisResponseDTO.setEjePsicologico(analisisEntity.getEjePsicologico());
		analisisResponseDTO.setId(analisisEntity.getId());
		analisisResponseDTO.setSustratoFilosofico(analisisEntity.getSustratoFilosofico());
		analisisResponseDTO.setMapaSensaciones(analisisEntity.getMapaSensaciones());
		analisisResponseDTO.setIntroduccionTeorica(analisisEntity.getIntroduccionTeorica());
		
	
		
		return analisisResponseDTO;
	}

}
