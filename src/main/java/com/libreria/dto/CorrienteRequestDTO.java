package com.libreria.dto;

import jakarta.persistence.Column;

public class CorrienteRequestDTO {
	
	private String nombre;
	private String periodo;
	@Column(length = 1000)
	private String descripcion;
	private String fundamentos;
	private String caracteristicas;
	
	public CorrienteRequestDTO() {
		
	}
	
	public CorrienteRequestDTO(String nombre, String periodo, String descripcion, String fundamentos,
			String caracteristicas) {
		super();
		this.nombre = nombre;
		this.periodo = periodo;
		this.descripcion = descripcion;
		this.fundamentos = fundamentos;
		this.caracteristicas = caracteristicas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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
