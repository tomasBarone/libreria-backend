package com.libreria.dto;

import com.libreria.model.Categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LibroResponseDTO {
	

	@Schema(example = "El Aleph")
	@NotBlank(message = "El titulo no puede estar vacio")
	private String titulo;
	
	@NotBlank(message = "El autor es obligatorio")
	@Schema(example = "Jorge Luis Borges")
	private String autor;
	
	@Schema(example = "Novela")
	private String categoriaNombre;
	
	@Schema(example = "1949")
	@Min(value = 1450, message = "El año debe ser posterior a la invencion de la imprenta")
	private int anioPublicacion;

	
	
	public LibroResponseDTO() {
		
	}
	
    
	
	public LibroResponseDTO(@NotBlank(message = "El titulo no puede estar vacio") String titulo,
			@NotBlank(message = "El autor es obligatorio") String autor) {
		super();
		this.titulo = titulo;
		this.autor = autor;
	}



	public LibroResponseDTO(@NotBlank(message = "El titulo no puede estar vacio") String titulo,
			@NotBlank(message = "El autor es obligatorio") String autor, String categoriaNombre,
			@Min(value = 1450, message = "El año debe ser posterior a la invencion de la imprenta") int anioPublicacion) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.categoriaNombre = categoriaNombre;
		this.anioPublicacion = anioPublicacion;
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


	public String getCategoriaNombre() {
		return categoriaNombre;
	}


	public void setCategoriaId(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}


	public int getAnioPublicacion() {
		return anioPublicacion;
	}


	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}
	
	
	
	
	

}
