package com.libreria.dto;

import jakarta.persistence.Column;

public class LibroAnalisisRequestDTO {
	
	private Long id;
	private String libro;
	private String introduccionTeorica;
	private String mapaSensaciones;
	private String sustratoFilosofico;
	private String ejePsicologico;
	
	public LibroAnalisisRequestDTO() {
		
	}
	
	public LibroAnalisisRequestDTO(Long id, String libro, String introduccionTeorica, String mapaSensaciones, String sustratoFilosofico, String ejePsicologico) {
		
		this.id = id;
		this.libro = libro;
		this.introduccionTeorica = introduccionTeorica;
		this.mapaSensaciones = mapaSensaciones;
		this.sustratoFilosofico = sustratoFilosofico;
		this.ejePsicologico = ejePsicologico;
		
		
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String getIntroduccionTeorica() {
		return introduccionTeorica;
	}

	public void setIntroduccionTeorica(String introduccionTeorica) {
		this.introduccionTeorica = introduccionTeorica;
	}

	public String getMapaSensaciones() {
		return mapaSensaciones;
	}

	public void setMapaSensaciones(String mapaSensaciones) {
		this.mapaSensaciones = mapaSensaciones;
	}

	public String getSustratoFilosofico() {
		return sustratoFilosofico;
	}

	public void setSustratoFilosofico(String sustratoFilosofico) {
		this.sustratoFilosofico = sustratoFilosofico;
	}

	public String getEjePsicologico() {
		return ejePsicologico;
	}

	public void setEjePsicologico(String ejePsicologico) {
		this.ejePsicologico = ejePsicologico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
