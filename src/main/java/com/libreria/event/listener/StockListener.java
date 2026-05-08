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

    @EventListener
    public void procesarCambioStock(LibroCompradoEvent event) {
        
        libroService.actualizarStock(event.getLibroId(), event.getCantidad());
    }
	
}
