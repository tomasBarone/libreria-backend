package com.libreria.dto;

public class SubgeneroResponseDTO {
	
	private String nombre;
	private String descripcion;
	private String generoNombre;
	
	
	public SubgeneroResponseDTO() {
		
	}
	
	public SubgeneroResponseDTO(String nombre, String descripcion, String generoNombre) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.generoNombre = generoNombre;
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

	public String getGeneroNombre() {
		return generoNombre;
	}

	public void setGeneroNombre(String generoNombre) {
		this.generoNombre = generoNombre;
	}
	
	
	
	
	

}
