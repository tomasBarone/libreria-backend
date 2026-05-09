package com.libreria.event;


/**
 * Representa un evento de dominio. Es un objeto inmutable (final) que 
 * transporta los datos mínimos necesarios para que otros componentes reaccionen.
 */
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
