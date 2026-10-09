package com.libreria.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.LibroResponseDTO;
import com.libreria.model.UserEntity;
import com.libreria.repository.UserRepository;
import com.libreria.service.BibliotecaService;

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {
    
    private final BibliotecaService bibliotecaService;
    private final UserRepository userRepository;
    
    public BibliotecaController(BibliotecaService bibliotecaService, UserRepository userRepository) {
        this.bibliotecaService = bibliotecaService;
        this.userRepository = userRepository;
    }

    // Retorna directamente la lista de libros guardados usando LibroResponseDTO
    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> obtenerLecturas(Authentication authentication) {
        String username = authentication.getName();
        
        UserEntity userEntity = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    
        List<LibroResponseDTO> libros = bibliotecaService.obtenerLecturas(userEntity.getId());
        
        return ResponseEntity.ok(libros);
    }
    
    @GetMapping("/check/{libroId}")
    public ResponseEntity<Map<String, Boolean>> estaGuardado(@PathVariable Long libroId, Authentication authentication) {
        String username = authentication.getName();
        
        UserEntity userEntity = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        boolean guardado = bibliotecaService.estaGuardado(userEntity.getId(), libroId);
        return ResponseEntity.ok(Collections.singletonMap("guardado", guardado));
    }
    
    @PostMapping("/toggle/{libroId}")
    public ResponseEntity<Map<String, Object>> alternarGuardado(@PathVariable Long libroId, Authentication authentication) {
        String username = authentication.getName();
        
        UserEntity userEntity = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        boolean estadoFinal = bibliotecaService.alternarGuardado(userEntity.getId(), libroId);
        String mensaje = estadoFinal ? "Libro guardado en tus lecturas" : "Libro eliminado de tus lecturas";
        
        return ResponseEntity.ok(Map.of("guardado", estadoFinal, "mensaje", mensaje));
    }
}