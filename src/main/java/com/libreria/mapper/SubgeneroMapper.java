package com.libreria.mapper;

import org.mapstruct.Mapper;

import com.libreria.dto.SubgeneroResponseDTO;
import com.libreria.model.Subgenero;

@Mapper(componentModel = "spring")
public class SubgeneroMapper {
	
	
	public SubgeneroResponseDTO toResponseDTO(Subgenero subgenero) {
		
		SubgeneroResponseDTO subgeneroDTO = new SubgeneroResponseDTO();
		
		subgeneroDTO.setNombre(subgenero.getNombre());
		subgeneroDTO.setDescripcion(subgenero.getDescripcion());
		subgeneroDTO.setGeneroNombre(subgenero.getGenero().getNombre());
		subgeneroDTO.setId(subgenero.getId());
		
		return subgeneroDTO;
		
	}
	
	

}
