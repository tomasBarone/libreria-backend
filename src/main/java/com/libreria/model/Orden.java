package com.libreria.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime fechaCreacion;
	
	private BigDecimal total;
	
	private String estado;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "orden_id")
	private List<DetalleOrden> detalles = new ArrayList<>();
	
	public Orden() {
		
	}

	public Orden(Long id, LocalDateTime fechaCreacion, BigDecimal total, String estado, List<DetalleOrden> detalles) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.total = total;
		this.estado = estado;
		this.detalles = detalles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetalleOrden> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleOrden> detalles) {
		this.detalles = detalles;
	}
	
	public void calcularTotal() {
		this.total = detalles.stream().map(DetalleOrden::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
