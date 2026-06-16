package com.libreria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libreria.dto.CorrienteRequestDTO;
import com.libreria.dto.CorrienteResponseDTO;
import com.libreria.mapper.CorrienteLiterariaMapper;
import com.libreria.model.CorrienteLiteraria;
import com.libreria.repository.CorrienteRepository;

@Service
public class CorrienteLiterariaService {
	
	CorrienteRepository corrienteRepository;
	CorrienteLiterariaMapper corrienteMapper;
	
	

	public CorrienteLiterariaService(CorrienteRepository corrienteRepository, CorrienteLiterariaMapper corrienteMapper) {
		super();
		this.corrienteRepository = corrienteRepository;
		this.corrienteMapper = corrienteMapper;
	}


	public List<CorrienteResponseDTO> listarTodos() {
    
		List<CorrienteLiteraria> corrientes = corrienteRepository.findAll();
        
        return corrientes.stream()
                .map(corrienteMapper::toResponseDTO) // Mapea a ID y Nombre
                .toList();
    }
	

	public Object guardar(CorrienteLiteraria movimiento) {
		// TODO Auto-generated method stub
		return null;
	}

}
