package com.libreria.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.libreria.dto.LibroDTO;
import com.libreria.dto.LibroResponseDTO;
import com.libreria.model.Libro;

@Mapper(componentModel = "spring")
public abstract class LibroMapper {

    @Mapping(source = "corriente.nombre", target = "corrienteNombre")
    @Mapping(source = "subgenero.nombre", target = "subgeneroNombre")
    @Mapping(source = "subgenero.genero.nombre", target = "generoNombre")
    public abstract LibroResponseDTO toResponseDTO(Libro libro);

    @Mapping(target = "corriente", ignore = true)
    @Mapping(target = "subgenero", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Libro toEntity(LibroDTO libroDTO);

    public abstract void updateEntityFromDto(LibroDTO dto, @MappingTarget Libro entity);

    @AfterMapping
    protected void UrlImagen(Libro libro, @MappingTarget LibroResponseDTO targetDTO) {
        if (libro.getImagenNombre() != null && !libro.getImagenNombre().isBlank()) {
            String url = libro.getImagenNombre();
            
            // Corregimos por si alguna URL vieja en la base de datos quedó con https:/ sin doble barra
            if (url.startsWith("https:/") && !url.startsWith("https://")) {
                url = url.replace("https:/", "https://");
            }
            
            targetDTO.setImagenUrl(url);
        }
    }
}