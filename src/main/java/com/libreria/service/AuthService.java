package com.libreria.service;


import com.libreria.model.RoleEnum;
import com.libreria.dto.AuthResponseDTO;
import com.libreria.dto.LoginRequest;
import com.libreria.dto.UserRegistrationDTO;
import com.libreria.dto.UserResponseDTO;
import com.libreria.model.RoleEntity;
import com.libreria.model.UserEntity;
import com.libreria.repository.RoleRepository;
import com.libreria.repository.UserRepository;
import com.libreria.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;

    
    
    public AuthResponseDTO authenticateUser(LoginRequest loginRequest) {
        
    	// 1. Autenticar las credenciales
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );

        // 2. Generar el token
        String token = jwtUtils.generateToken(authentication.getName());

     // Extraemos los roles de la autenticación
        Set<String> roles = authentication.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return new AuthResponseDTO(token, authentication.getName(), roles);
    }
    
    
    public UserResponseDTO registerUser(UserRegistrationDTO registrationDTO) {
        
    	// 1. Los datos viajan en el UserRegistrationDTO (Entrada)
        UserEntity entity = new UserEntity();
        entity.setUsername(registrationDTO.getUsername());
        
        //encriptar
        entity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        
        
        //Buscamos el rol "USER" en la base de datos
         
        RoleEntity userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Error: El rol USER no existe en la base de datos."));
        
        entity.setRoles(Set.of(userRole)); // Le asignamos el rol
        
        
        // 2. Se guardan en la BD
        UserEntity savedUser = userRepository.save(entity);
        
     // 3. Mapeo manual de Entidad a DTO de salida (sin password ni token)
        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        
        return response;
        
}
    
   
}