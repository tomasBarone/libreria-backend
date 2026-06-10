package com.libreria.dto;



import com.libreria.model.CorrienteLiteraria;
import com.libreria.model.Subgenero;

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
	
	private Long id;
	@Schema(example = "El Aleph")
	@NotBlank(message = "El titulo no puede estar vacio")
	private String titulo;
	
	@NotBlank(message = "El autor es obligatorio")
	@Schema(example = "Jorge Luis Borges")
	private String autor;
	
	
	@Schema(example = "1949")
	@Min(value = 1450, message = "El año debe ser posterior a la invencion de la imprenta")
	private int anioPublicacion;
	
	private String corrienteNombre; 
	
    private String subgeneroNombre;
    
    private String generoNombre; 

	
	
	public LibroResponseDTO() {
		
	}
	
    
	
	public LibroResponseDTO(@NotBlank(message = "El titulo no puede estar vacio") String titulo,
			@NotBlank(message = "El autor es obligatorio") String autor) {
		super();
		this.titulo = titulo;
		this.autor = autor;
	}



	public LibroResponseDTO(Long id, String titulo, String autor, int anioPublicacion, String corrienteNombre, String subgeneroNombre, String generoNombre) {
                     this.id = id;
                     this.titulo = titulo;
                     this.autor = autor;
                     this.anioPublicacion = anioPublicacion;
                     this.corrienteNombre = corrienteNombre;
                     this.subgeneroNombre = subgeneroNombre;
                     this.generoNombre = generoNombre;
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
	
	
	
	
	
	

}
