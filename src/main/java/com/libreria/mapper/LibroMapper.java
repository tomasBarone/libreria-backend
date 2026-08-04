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
        String nombreOUrl = libro.getImagenNombre();
        
        if (nombreOUrl != null && !nombreOUrl.isBlank()) {
            
            // 1. Si es una URL de Cloudinary o HTTP(S) externa
            if (nombreOUrl.contains("cloudinary.com") || nombreOUrl.startsWith("http://") || nombreOUrl.startsWith("https://") || nombreOUrl.startsWith("https:/")) {
                
                // Sanitizamos si en la base quedó guardada con una sola barra ("https:/")
                if (nombreOUrl.startsWith("https:/") && !nombreOUrl.startsWith("https://")) {
                    nombreOUrl = nombreOUrl.replace("https:/", "https://");
                }
                
                targetDTO.setImagenUrl(nombreOUrl);
                
            } else {
                // 2. Si es un archivo local guardado en la carpeta /uploads del servidor
                String urlCompleta = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/uploads/")
                        .path(nombreOUrl)
                        .toUriString();
                
                targetDTO.setImagenUrl(urlCompleta);
            }
        }
    }
}