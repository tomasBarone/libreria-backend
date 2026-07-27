package com.libreria.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
	
	@NotBlank(message = "El nombre de usuario es obligatorio")
	@Size(min = 3, max = 20, message = "El usuario debe tener entre 3 y 20 caracteres")
	@Pattern(
	    regexp = "^[a-zA-Z0-9_.]+$", 
	    message = "El nombre de usuario no puede contener espacios ni caracteres especiales (solo letras, números, '_' y '.')"
	)
	private String username;
	
	@NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
	
	
	@NotBlank(message = "El email no puede estar vacío")
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|hotmail\\.com|outlook\\.com|yahoo\\.com|live\\.com)$",
        message = "Debe ingresar una casilla de correo real válida (gmail, hotmail, outlook, yahoo)"
    )
    private String email;
	
	
    private Set<String> roles;
	
    
    
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    

}
