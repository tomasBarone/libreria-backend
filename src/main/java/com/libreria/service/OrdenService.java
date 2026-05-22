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

	    List<DetalleOrden> detalles = items.stream().map(item -> {
	        Libro libro = libroRepository.findById(item.getLibroId())
	                .orElseThrow(() -> new RuntimeException("El libro con ID " + item.getLibroId() + " no existe."));
	        
	        // 1. Creamos la instancia una sola vez usando el constructor que calcula los subtotales automáticamente
	        DetalleOrden detalle = new DetalleOrden(libro, item.getCantidad());

	        // 2. Publicamos el evento síncrono local para impactar el stock y limpiar Redis
	        eventPublisher.publishEvent(new LibroCompradoEvent(item.getLibroId(), item.getCantidad()));

	        // 3. Retornamos la instancia correcta ya calculada
	        return detalle;
	    }).collect(Collectors.toList());

	    orden.getDetalles().addAll(detalles);
	    orden.calcularTotal(); // Aseguramos que la orden sume todos sus subtotales
	    
	    return ordenRepository.save(orden);
	}

}
