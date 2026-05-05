package com.libreria.dto;

public class DetalleOrdenRequest {

	private Long libroId;
    private Integer cantidad;

    
    public DetalleOrdenRequest() {
    }

    public DetalleOrdenRequest(Long libroId, Integer cantidad) {
        this.libroId = libroId;
        this.cantidad = cantidad;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
	
}
