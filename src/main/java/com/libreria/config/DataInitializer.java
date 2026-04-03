package com.libreria.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.libreria.model.RoleEntity;
import com.libreria.model.RoleEnum;
import com.libreria.model.UserEntity;
import com.libreria.repository.RoleRepository;
import com.libreria.repository.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        // 1. CREAR ROLES si no existen
        // Buscamos si el rol ADMIN ya está en la tabla 'roles'
        RoleEntity adminRole = roleRepository.findByRoleName(RoleEnum.ADMIN)
                .orElseGet(() -> roleRepository.save(new RoleEntity(RoleEnum.ADMIN)));

        RoleEntity userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseGet(() -> roleRepository.save(new RoleEntity(RoleEnum.USER)));

        // 2. CREAR USUARIO ADMIN si no existe
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("admin");
            // Encriptamos la clave antes de guardarla 
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            
            // Asignamos el objeto RoleEntity que recuperamos/creamos antes
            adminUser.setRoles(Set.of(adminRole));

            userRepository.save(adminUser);
            System.out.println(">>> [DataInitializer] Usuario 'admin' creado exitosamente.");
        }
    }
}