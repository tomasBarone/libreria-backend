package com.libreria.service;

import com.libreria.event.LibroCompradoEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaService {
	
	private final ApplicationEventPublisher eventPublisher;

    public VentaService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    
    @Transactional
    public void registrarVenta(Long libroId, Integer cantidad) {
        // 1. Aquí iría: ventaRepository.save(new Venta(libroId, cantidad));
        System.out.println("Venta registrada en base de datos.");

        // 2. Publicamos el evento para que otros servicios se enteren
        LibroCompradoEvent evento = new LibroCompradoEvent(libroId, cantidad);
        eventPublisher.publishEvent(evento); 
    }

}
