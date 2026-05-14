package com.libreria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subgeneros")
public class Subgenero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "genero_id")
	private GeneroLiterario genero;

	public Subgenero(Long id, String nombre, String descripcion, GeneroLiterario genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public GeneroLiterario getGenero() {
		return genero;
	}

	public void setGenero(GeneroLiterario genero) {
		this.genero = genero;
	}
	
	
	
}
