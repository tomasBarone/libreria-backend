package com.libreria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "corrientes_literarias")
public class CorrienteLiteraria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String perdiodo;
	@Column(length = 1000)
	private String descripcion;
	private String fundamentos;
	private String caracteristicas;
	
	
	public CorrienteLiteraria() {
		
	}


	public CorrienteLiteraria( String nombre, String perdiodo, String descripcion, String fundamentos,
			String caracteristicas) {
		super();
	
		this.nombre = nombre;
		this.perdiodo = perdiodo;
		this.descripcion = descripcion;
		this.fundamentos = fundamentos;
		this.caracteristicas = caracteristicas;
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


	public String getPerdiodo() {
		return perdiodo;
	}


	public void setPerdiodo(String perdiodo) {
		this.perdiodo = perdiodo;
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
