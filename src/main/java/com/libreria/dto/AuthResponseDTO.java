package com.libreria.dto;

import java.util.Set;

public class AuthResponseDTO {
    private String token;
    private String username;
    private Set<String> roles; // Opcional: útil para que el Frontend sepa qué mostrar

    // Constructor vacío (necesario para la serialización de JSON)
    public AuthResponseDTO() {}

    public AuthResponseDTO(String token, String username, Set<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    // Getters y Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}