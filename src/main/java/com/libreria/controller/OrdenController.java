package com.libreria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.DetalleOrdenRequest;
import com.libreria.model.DetalleOrden;
import com.libreria.model.Orden;
import com.libreria.service.OrdenService;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {
	
	private final OrdenService ordenService;

	public OrdenController(OrdenService ordenService) {
		super();
		this.ordenService = ordenService;
	}
	
	
	/**
	 * Endpoint para procesar una nueva compra.
	 * Recibe una lista de libros y cantidades (DTOs) desde el cliente.
	 */
	@PostMapping
	public ResponseEntity<Orden> crearOrden(@RequestBody List<DetalleOrdenRequest> items, @RequestHeader(value = "X-User-Email", defaultValue = "tu.email.real@gmail.com") String email){
		// Delega la lógica de negocio al servicio y retorna la orden creada
		
		System.out.println(email);
		Orden nuevaOrden = ordenService.crearOrden(items, email);
        return ResponseEntity.ok(nuevaOrden);
		
	}
	

}
