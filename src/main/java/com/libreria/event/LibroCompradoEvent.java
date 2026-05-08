package com.libreria.event;

public class LibroCompradoEvent {
	
	private final Long libroId;
    private final Integer cantidad;

    public LibroCompradoEvent(Long libroId, Integer cantidad) {
        this.libroId = libroId;
        this.cantidad = cantidad;
    }

    public Long getLibroId() { return libroId; }
    public Integer getCantidad() { return cantidad; }

}
