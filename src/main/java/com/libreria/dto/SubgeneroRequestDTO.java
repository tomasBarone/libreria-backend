package com.libreria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubgeneroRequestDTO {
	
	@NotBlank(message = "El nombre del subgenero es requerido")
	private String nombre;
	@NotBlank(message = "La descripcion del subgenero es requerido")
	private String descripcion;
	@NotNull(message = "El genero es obligatorio")
	private Long genero;
	
	public SubgeneroRequestDTO() {
		
	}

	public SubgeneroRequestDTO( String nombre, String descripcion, Long genero) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getGenero() {
		return genero;
	}

	public void setGenero(Long genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "SubgeneroRequestDTO [nombre=" + nombre + ", descripcion=" + descripcion + ", genero=" + genero + "]";
	}
	
	
	

}
