package com.libreria.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Definimos la carpeta física en la raíz del proyecto backend
        String carpetaUploads = Paths.get("uploads").toAbsolutePath().toUri().toString();
        
        // Mapeo: Cualquier petición que vaya a http://localhost:8080/uploads/*** 
      
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(carpetaUploads);
    }
}