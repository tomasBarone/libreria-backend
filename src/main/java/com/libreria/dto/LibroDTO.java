package com.libreria.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LibroDTO {
	
	@NotBlank(message = "El titulo es requerido")
	private String titulo;
	
	@NotBlank(message = "El autor es requerido")
	private String autor;
	
	@Min(value = 1450, message = "Año invalido")
	private int anioPublicacion;
	
	@Min(0)
	private int ejemplares;
	
	private Long categoriaId;
	
	private String isbn;
	
	@Min(value = 1000, message = "precio invalido")
	private BigDecimal precio;

	public LibroDTO(@NotBlank(message = "El titulo es requerido") String titulo,
			@NotBlank(message = "El autor es requerido") String autor,
			@Min(value = 1450, message = "Año invalido") int anioPublicacion, @Min(0) int ejemplares,
			Long categoriaId, String isbn,@Min(value = 1000, message = "precio invalido") BigDecimal precio) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.ejemplares = ejemplares;
		this.categoriaId = categoriaId;
		this.isbn = isbn;
		this.precio = precio;
		
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
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
	
	
	

}
