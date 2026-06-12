package com.libreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.model.CorrienteLiteraria;
import com.libreria.service.MovimientoService;

@RestController
@RequestMapping("/api/movimientos")
public class CorrienteLiterariaController {

   
    private final  MovimientoService movimientoService;
    
    

 
    public CorrienteLiterariaController(MovimientoService movimientoService) {
		super();
		this.movimientoService = movimientoService;
	}


	@GetMapping
    public ResponseEntity<Object> listarTodos() {
        return ResponseEntity.ok(movimientoService.listarTodos());
    }
    
    
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody CorrienteLiteraria movimiento) {
        return ResponseEntity.ok(movimientoService.guardar(movimiento));
    }
}