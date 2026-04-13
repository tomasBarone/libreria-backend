package com.libreria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CategoriaDTO {
	
	@NotBlank(message = "El nombre de la categoria es obligatorio")
	private String nombre;
	
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(@NotBlank(message = "El nombre de la categoria es obligatorio") String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	

}
