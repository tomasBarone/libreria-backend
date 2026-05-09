package com.libreria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.libreria.dto.DetalleOrdenRequest;
import com.libreria.model.DetalleOrden;
import com.libreria.model.Libro;
import com.libreria.model.Orden;
import com.libreria.repository.LibroRepository;
import com.libreria.repository.OrdenRepository;
import com.libreria.event.LibroCompradoEvent;

import jakarta.transaction.Transactional;

@Service
public class OrdenService {
	
	private final OrdenRepository ordenRepository;
	private final LibroRepository libroRepository;
	private final ApplicationEventPublisher eventPublisher;
	
	
	public OrdenService(OrdenRepository ordenRepository,  LibroRepository libroRepository,   ApplicationEventPublisher eventPublisher) {
                   this.ordenRepository = ordenRepository;
                   this.libroRepository = libroRepository;
                   this.eventPublisher = eventPublisher;
}
	
	
	/**
	 * @Transactional asegura que si algo falla (ej. stock insuficiente), 
	 * todos los cambios en la base de datos vuelvan atrás (Rollback).
	 */
	
	@Transactional
    public Orden crearOrden(List<DetalleOrdenRequest> items) {
        Orden orden = new Orden();
        orden.setEstado("PENDIENTE");

     // Convertimos los DTOs recibidos en entidades de DetalleOrden
        List<DetalleOrden> detalles = items.stream().map(item -> {
        	// Buscamos el libro en la DB
            Libro libro = libroRepository.findById(item.getLibroId())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
            
            
         // PASO A: Lógica local (persistencia de la orden)
            DetalleOrden detalle = new DetalleOrden(libro, item.getCantidad());

            // PASO B: Publicar Evento
            // Esto activará el StockListener para limpiar caché y actualizar stock de forma desacoplada
            eventPublisher.publishEvent(new LibroCompradoEvent(item.getLibroId(), item.getCantidad()));

        
            
            
         // Creamos la relación entre el detalle y el producto
            return new DetalleOrden(libro, item.getCantidad());
        }).collect(Collectors.toList());

     // Vinculamos detalles a la orden y calculamos el monto total
        orden.getDetalles().addAll(detalles);
        orden.calcularTotal();
        
     // Persistimos la orden final
        return ordenRepository.save(orden);
    }

}
