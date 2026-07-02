package com.libreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.CorrienteRequestDTO;
import com.libreria.dto.CorrienteResponseDTO;
import com.libreria.model.CorrienteLiteraria;
import com.libreria.service.CorrienteLiterariaService;

@RestController
@RequestMapping("/api/movimientos")
public class CorrienteLiterariaController {

   
    private final  CorrienteLiterariaService movimientoService;
    
    

 
    public CorrienteLiterariaController(CorrienteLiterariaService movimientoService) {
		super();
		this.movimientoService = movimientoService;
	}


	@GetMapping("/all")
    public ResponseEntity<List<CorrienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoService.listarTodos());
    }
    
    
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody CorrienteLiteraria movimiento) {
        return ResponseEntity.ok(movimientoService.guardar(movimiento));
    }
    
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CorrienteResponseDTO> actualizarCorriente(@PathVariable Long id, @RequestBody CorrienteRequestDTO corriente){
		
    	CorrienteResponseDTO corrienteActualizada = movimientoService.actualizar(id,corriente);
    	
    	return new ResponseEntity<>(corrienteActualizada, HttpStatus.OK);
    	
    }
    
}