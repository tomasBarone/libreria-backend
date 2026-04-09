package com.libreria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.libreria.dto.LibroDTO;
import com.libreria.dto.LibroResponseDTO;
import com.libreria.model.Libro;

@Mapper(componentModel = "spring") // Esto permite inyectarlo con @Autowired
public interface LibroMapper {

    // Convierte de Entidad a DTO (para las respuestas)
    LibroResponseDTO toResponseDTO(Libro libro);

    // Convierte de DTO a Entidad (para guardar)
    @Mapping(target = "id", ignore = true) // El ID lo genera la DB
    Libro toEntity(LibroDTO libroDTO);

    // Para actualizar una entidad existente sin crear una nueva
    void updateEntityFromDto(LibroDTO dto, @MappingTarget Libro entity);
}