package com.libreria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros_analisis")
public class LibroAnalisis {

	@Id
	private Long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
	@Column(columnDefinition = "TEXT")
	private String introduccionTeorica;
	
	@Column(columnDefinition = "TEXT")
	private String mapaSensaciones;
	
	private String sustratoFilosofico;
	private String ejePsicologico;
	
	
	public LibroAnalisis() {
		
	}
	
	public LibroAnalisis(Long id, Libro libro, String introduccionTeorica, String mapaSensaciones,
			String sustratoFilosofico, String ejePsicologico) {
		super();
		this.id = id;
		this.libro = libro;
		this.introduccionTeorica = introduccionTeorica;
		this.mapaSensaciones = mapaSensaciones;
		this.sustratoFilosofico = sustratoFilosofico;
		this.ejePsicologico = ejePsicologico;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
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

	@Override
	public String toString() {
		return "LibroAnalisis [id=" + id + ", libro=" + libro + ", introduccionTeorica=" + introduccionTeorica
				+ ", mapaSensaciones=" + mapaSensaciones + ", sustratoFilosofico=" + sustratoFilosofico
				+ ", ejePsicologico=" + ejePsicologico + "]";
	}
	
	
}
