package com.libreria.dto;

public class SubgeneroResponseDTO {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private String generoNombre;
	
	
	public SubgeneroResponseDTO() {
		
	}
	
	public SubgeneroResponseDTO(Long id,String nombre, String descripcion, String generoNombre) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.generoNombre = generoNombre;
		this.id = id;
		
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	

}
