
package com.libreria.controller;

import com.libreria.dto.LoginRequest;
import com.libreria.dto.UserRegistrationDTO;
import com.libreria.dto.UserResponseDTO;
import com.libreria.security.JwtUtils;
import com.libreria.service.AuthService;

import jakarta.validation.Valid;

import com.libreria.dto.AuthResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;
	
	
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequest loginRequest) {
       
    
        AuthResponseDTO authResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(authResponse);
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        
        UserResponseDTO response = authService.registerUser(registrationDTO);
        return ResponseEntity.ok(response);
    }

}
