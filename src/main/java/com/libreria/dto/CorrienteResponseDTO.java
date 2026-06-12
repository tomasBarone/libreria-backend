package com.libreria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;


@JsonInclude(JsonInclude.Include.NON_NULL) // Oculta automáticamente lo que sea null
public class CorrienteResponseDTO {
	
	
    private Long id;
	private String nombre;
	private String periodo; 
	private String descripcion;
	private String fundamentos;
	private String caracteristicas;
	
	
	public CorrienteResponseDTO() {
		
	}


	public CorrienteResponseDTO(Long id, String nombre, String perdiodo, String descripcion, String fundamentos,
			String caracteristicas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.periodo = perdiodo;
		this.descripcion = descripcion;
		this.fundamentos = fundamentos;
		this.caracteristicas = caracteristicas;
	}
	
	
	public CorrienteResponseDTO(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String perdiodo) {
		this.periodo = perdiodo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getFundamentos() {
		return fundamentos;
	}


	public void setFundamentos(String fundamentos) {
		this.fundamentos = fundamentos;
	}


	public String getCaracteristicas() {
		return caracteristicas;
	}


	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	
}
