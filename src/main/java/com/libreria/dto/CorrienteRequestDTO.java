package com.libreria.dto;

import jakarta.persistence.Column;

public class CorrienteRequestDTO {
	
	private String nombre;
	private String periodo;
	@Column(length = 1000)
	private String descripcion;
	private String fundamentos;
	private String caracteristicas;

}
