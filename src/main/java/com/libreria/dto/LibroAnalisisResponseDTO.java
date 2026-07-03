package com.libreria.dto;

public class LibroAnalisisResponseDTO {
	
	private Long id;
	private String libro;
	private String  introduccionTeorica;
	private String mapaSensaciones;
	private String ejePsicologico;
	private String sustratoFilosofico;
	
	
	public LibroAnalisisResponseDTO() {
		
	}
	
	public LibroAnalisisResponseDTO(Long id, String libro, String introduccionTeorica, String mapaSensaciones, String ejePsicologico, String sustratoFilosofico) {
		this.id = id;
		this.libro = libro;
		this.introduccionTeorica = introduccionTeorica;
		this.mapaSensaciones = mapaSensaciones;
		this.ejePsicologico = ejePsicologico;
		this.sustratoFilosofico = sustratoFilosofico;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEjePsicologico() {
		return ejePsicologico;
	}

	public void setEjePsicologico(String ejePsicologico) {
		this.ejePsicologico = ejePsicologico;
	}

	public String getSustratoFilosofico() {
		return sustratoFilosofico;
	}

	public void setSustratoFilosofico(String sustratoFilosofico) {
		this.sustratoFilosofico = sustratoFilosofico;
	}
	
	

}
