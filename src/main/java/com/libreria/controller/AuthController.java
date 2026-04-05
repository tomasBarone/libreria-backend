
package com.libreria.controller;

import com.libreria.dto.LoginRequest;
import com.libreria.security.JwtUtils;
import com.libreria.service.AuthService;

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
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequest loginRequest) {
       
    
        Map<String,String> response = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

}
