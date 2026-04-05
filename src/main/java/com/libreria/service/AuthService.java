package com.libreria.service;

import com.libreria.dto.LoginRequest;
import com.libreria.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public Map<String, String> authenticateUser(LoginRequest loginRequest) {
        
    	// 1. Autenticar las credenciales
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );

        // 2. Generar el token
        String token = jwtUtils.generateToken(authentication.getName());

        // 3. Preparar la respuesta
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        
        return response;
    }
}