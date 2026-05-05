package com.libreria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.libreria.dto.DetalleOrdenRequest;
import com.libreria.model.DetalleOrden;
import com.libreria.model.Libro;
import com.libreria.model.Orden;
import com.libreria.repository.LibroRepository;
import com.libreria.repository.OrdenRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdenService {
	
	private final OrdenRepository ordenRepository;
	private final LibroRepository libroRepository;
	
	public OrdenService(OrdenRepository ordenRepository, LibroRepository libroRepository) {
        this.ordenRepository = ordenRepository;
        this.libroRepository = libroRepository;
    }
	
	
	@Transactional
    public Orden crearOrden(List<DetalleOrdenRequest> items) {
        Orden orden = new Orden();
        orden.setEstado("PENDIENTE");

        List<DetalleOrden> detalles = items.stream().map(item -> {
            Libro libro = libroRepository.findById(item.getLibroId())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            
            libro.reducirEjemplares(item.getCantidad());
            libroRepository.save(libro);

            return new DetalleOrden(libro, item.getCantidad());
        }).collect(Collectors.toList());

        orden.getDetalles().addAll(detalles);
        orden.calcularTotal();
        
        return ordenRepository.save(orden);
    }

}
