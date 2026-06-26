package com.libreria.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LibroResponseDTO {
	
	private Long id;
	@Schema(example = "El Aleph")
	private String titulo;
	
	
	@Schema(example = "Jorge Luis Borges")
	private String autor;
	
	private String isbn;
	
	
	@Schema(example = "1949")
	private int anioPublicacion;
	
	private String corrienteNombre; 
	
    private String subgeneroNombre;
    
    private String generoNombre; 
    
    private BigDecimal precio;
    
    private String sinopsis;
    
    private String imagenUrl;

	
	
	public LibroResponseDTO() {
		
	}
	
    
	
		public LibroResponseDTO(String titulo, String autor) {
			this.titulo = titulo;
			this.autor = autor;
		}



 
		public LibroResponseDTO(Long id, String titulo, String autor, int anioPublicacion, 
                String corrienteNombre, String subgeneroNombre, String generoNombre, BigDecimal precio, String isbn, String sinopsis, String imagenUrl) {
			this.id = id;
			this.titulo = titulo;
			this.autor = autor;
			this.anioPublicacion = anioPublicacion;
			this.corrienteNombre = corrienteNombre;
			this.subgeneroNombre = subgeneroNombre;
			this.generoNombre = generoNombre;
			this.precio = precio;
			this.isbn = isbn;
			this.sinopsis = sinopsis;
			this.imagenUrl = imagenUrl;
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



	public String getCorrienteNombre() {
		return corrienteNombre;
	}



	public void setCorrienteNombre(String corrienteNombre) {
		this.corrienteNombre = corrienteNombre;
	}



	public String getSubgeneroNombre() {
		return subgeneroNombre;
	}



	public void setSubgeneroNombre(String subgeneroNombre) {
		this.subgeneroNombre = subgeneroNombre;
	}



	public String getGeneroNombre() {
		return generoNombre;
	}



	public void setGeneroNombre(String generoNombre) {
		this.generoNombre = generoNombre;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public BigDecimal getPrecio() {
		return precio;
	}



	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getSinopsis() {
		return sinopsis;
	}



	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}



	public String getImagenUrl() {
		return imagenUrl;
	}



	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	
	
	
	
	
	
	
	

}
