package com.libreria.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class CorrienteRequestDTO {
	
	@NotBlank(message = "El nombre es requerido")
	private String nombre;
	@NotBlank(message = "El periodo es requerido")
	private String periodo;
	@Column(length = 1000)
	@NotBlank(message = "La descripcion es requerida")
	private String descripcion;
	@NotBlank(message = "Los fundamentos son requeridos")
	private String fundamentos;
	@NotBlank(message = "Las caracteristicas son requeridas")
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
