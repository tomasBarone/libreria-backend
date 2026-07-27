package com.libreria.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.libreria.dto.AuthResponseDTO;
import com.libreria.dto.LoginRequest;
import com.libreria.dto.UserRegistrationDTO;
import com.libreria.dto.UserResponseDTO;
import com.libreria.model.RoleEntity;
import com.libreria.model.RoleEnum;
import com.libreria.model.UserEntity;
import com.libreria.repository.RoleRepository;
import com.libreria.repository.UserRepository;
import com.libreria.security.JwtUtils;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository; 
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    // Inyección por constructor (Buena práctica recomendada)
    public AuthService(AuthenticationManager authenticationManager,
                       JwtUtils jwtUtils,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public AuthResponseDTO authenticateUser(LoginRequest loginRequest) {
        
        // 1. Autenticar las credenciales
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );

        // 2. Extraer los roles
        Set<String> roles = authentication.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        // 3. Generar el token pasándole el username Y los roles
        String token = jwtUtils.generateToken(authentication.getName(), roles);

        // 4. Devolver la respuesta completa
        return new AuthResponseDTO(token, authentication.getName(), roles);
    }
    
    public UserResponseDTO registerUser(UserRegistrationDTO registrationDTO) {
        
        // 1. Validaciones de existencia previa
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está registrado.");
        }
        
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está asociado a otra cuenta.");
        }

        // 2. Crear y popular la entidad
        UserEntity entity = new UserEntity();
        entity.setUsername(registrationDTO.getUsername());
        entity.setEmail(registrationDTO.getEmail());
        entity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        
        // 3. Asignar el rol "USER" por defecto
        RoleEntity userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Error: El rol USER no existe en la base de datos."));
        
        entity.setRoles(Set.of(userRole));
        
        // 4. Guardar en la BD
        UserEntity savedUser = userRepository.save(entity);
        
        // 5. Mapeo a DTO de salida (incluyendo roles mapeados a String)
        Set<String> rolesString = savedUser.getRoles().stream()
                .map(role -> role.getRoleName().name()) // o role.getRoleName().toString()
                .collect(Collectors.toSet());

        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setRoles(rolesString); // 👈 CORREGIDO: Mapeo de roles agregado
        
        return response;
    }
}