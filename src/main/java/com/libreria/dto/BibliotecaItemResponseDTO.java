package com.libreria.dto;

public class BibliotecaItemResponseDTO {
	
	private Long id;
	private String usuario;
	private Long libroId;
	
	public BibliotecaItemResponseDTO() {
		
	}

	public BibliotecaItemResponseDTO(Long id, String usuario, Long libroId) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.libroId = libroId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

	public Long getLibroId() {
		return libroId;
	}

	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}
	
	
	

}
