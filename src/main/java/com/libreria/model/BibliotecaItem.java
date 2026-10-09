package com.libreria.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "biblioteca_usuario")
public class BibliotecaItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id" , nullable = false)
	private UserEntity usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
	@Column(name = "fecha_guardado")
	private LocalDateTime fechaGuardado;
	
	@PrePersist
	public void prePersist() {
		
	}
	
	public BibliotecaItem() {
		
	}
	
	public BibliotecaItem(UserEntity usuario, Libro libro) {
		this.usuario = usuario;
		this.libro = libro;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public LocalDateTime getFechaGuardado() {
		return fechaGuardado;
	}

	public void setFechaGuardado(LocalDateTime fechaGuardado) {
		this.fechaGuardado = fechaGuardado;
	}
	
	
	
	
	
}
