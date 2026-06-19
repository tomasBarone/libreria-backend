package com.libreria.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LibroDTO {
	
	@NotBlank(message = "El titulo es requerido")
	private String titulo;
	
	@NotBlank(message = "El autor es requerido")
	private String autor;
	
	@Min(value = 1450, message = "Año invalido")
	private int anioPublicacion;
	
	@Min(0)
	private int ejemplares;
	
	@NotNull(message = "La corriente literaria es obligatoria")
	private Long corrienteId;
	
	@NotNull(message = "El subgénero es obligatorio")
	private Long subgeneroId;
	
	private String isbn;
	
	@Min(value = 1000, message = "precio invalido. El minimo es 1000")
	private BigDecimal precio;
	
	private String sinopsis;
	
	
	public LibroDTO() {
		
	}

	public LibroDTO(String titulo, String autor, int anioPublicacion, int ejemplares,
			Long corrienteId, Long subgeneroId, String isbn, BigDecimal precio, String sinopsis) {
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.ejemplares = ejemplares;
		this.corrienteId = corrienteId;
		this.subgeneroId = subgeneroId;
		this.isbn = isbn;
		this.precio = precio;
		this.sinopsis = sinopsis;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}

	

	public Long getCorrienteId() {
		return corrienteId;
	}

	public void setCorrienteId(Long corrienteId) {
		this.corrienteId = corrienteId;
	}

	public Long getSubgeneroId() {
		return subgeneroId;
	}

	public void setSubgeneroId(Long subgeneroId) {
		this.subgeneroId = subgeneroId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	
	

}
