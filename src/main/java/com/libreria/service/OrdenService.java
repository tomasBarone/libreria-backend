package com.libreria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.dto.DetalleOrdenRequest;
import com.libreria.event.LibroCompradoEvent;
import com.libreria.model.DetalleOrden;
import com.libreria.model.Libro;
import com.libreria.model.Orden;
import com.libreria.repository.LibroRepository;
import com.libreria.repository.OrdenRepository;
import com.libreria.event.OrdenCreadaEvent;

@Service
public class OrdenService {
	
    private final OrdenRepository ordenRepository;
    private final LibroRepository libroRepository;
    private final ApplicationEventPublisher eventPublisher;
	
    public OrdenService(OrdenRepository ordenRepository, LibroRepository libroRepository, ApplicationEventPublisher eventPublisher) {
        this.ordenRepository = ordenRepository;
        this.libroRepository = libroRepository;
        this.eventPublisher = eventPublisher;
    }
	
    @Transactional
    public Orden crearOrden(List<DetalleOrdenRequest> items, String emailCliente) {
       
    	Orden orden = new Orden();
        orden.setEstado("COMPLETADA");

        List<DetalleOrden> detalles = items.stream().map(item -> {
            Libro libro = libroRepository.findById(item.getLibroId())
                    .orElseThrow(() -> new RuntimeException("El libro con ID " + item.getLibroId() + " no existe."));
            
            // VALIDACIÓN DE STOCK AGREGADA
            if (libro.getEjemplares() < item.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para el libro: " + libro.getTitulo() + 
                                               ". Disponible: " + libro.getEjemplares() + ", Solicitado: " + item.getCantidad());
            }

            DetalleOrden detalle = new DetalleOrden(libro, item.getCantidad());

            // Publicamos el evento síncrono local para impactar el stock y limpiar Redis
            eventPublisher.publishEvent(new LibroCompradoEvent(item.getLibroId(), item.getCantidad()));

            return detalle;
        }).collect(Collectors.toList());
        
        

        orden.getDetalles().addAll(detalles);
        orden.calcularTotal(); 
        
     // Guardamos la orden en PostgreSQL
        Orden ordenGuardada = ordenRepository.save(orden);
        
        
     // Publicamos el evento de la orden comiteada
        eventPublisher.publishEvent(new OrdenCreadaEvent(ordenGuardada, emailCliente));
        
        return ordenGuardada;
    }
}