package com.libreria.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class CategoriaResponseDTO {
	
	@NotBlank(message = "El nombre de la categoria es obligatorio")
	private String nombre;
	private String mensaje;
	private List<String> librosAsociados;
	
	
	public CategoriaResponseDTO() {
		
	}

	public CategoriaResponseDTO(@NotBlank(message = "El nombre de la categoria es obligatorio") String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<String> getLibrosAsociados() {
		return librosAsociados;
	}

	public void setLibrosAsociados(List<String> librosAsociados) {
		this.librosAsociados = librosAsociados;
	}
	
	
	
	

}
