package com.libreria.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.libreria.event.LibroCompradoEvent;
import com.libreria.service.LibroService;

@Component
public class StockListener {

	private final LibroService libroService; 

    public StockListener(LibroService libroService) {
        this.libroService = libroService;
    }

    
    /**
     * Este método se activa automáticamente cuando un LibroCompradoEvent es publicado.
     * Su función es actuar como puente entre la Orden y el Servicio de Libros/Caché.
     */
    @EventListener
    public void procesarCambioStock(LibroCompradoEvent event) {
        
    	// Llama al servicio de libros para actualizar el estado (esto dispara @CacheEvict del Nivel 5)
        libroService.actualizarStock(event.getLibroId(), event.getCantidad());
    }
	
}
