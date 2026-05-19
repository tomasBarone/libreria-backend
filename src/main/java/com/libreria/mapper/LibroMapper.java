package com.libreria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.libreria.dto.LibroDTO;
import com.libreria.dto.LibroResponseDTO;
import com.libreria.model.Libro;

@Mapper(componentModel = "spring")
public interface LibroMapper {

    // Ahora mapeamos el nombre de la corriente y el nombre del subgénero
    @Mapping(source = "corriente.nombre", target = "corrienteNombre")
    @Mapping(source = "subgenero.nombre", target = "subgeneroNombre")
    @Mapping(source = "subgenero.genero.nombre", target = "generoNombre") // ¡Mapeo profundo!
    LibroResponseDTO toResponseDTO(Libro libro);

    @Mapping(target = "corriente", ignore = true)
    @Mapping(target = "subgenero", ignore = true)
    @Mapping(target = "id", ignore = true)
    Libro toEntity(LibroDTO libroDTO);

    void updateEntityFromDto(LibroDTO dto, @MappingTarget Libro entity);
}