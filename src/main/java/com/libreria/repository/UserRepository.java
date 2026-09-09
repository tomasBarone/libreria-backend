package com.libreria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	// Verificaciones Case-Insensitive para el Registro
    boolean existsByUsernameIgnoreCase(String username);
    boolean existsByEmailIgnoreCase(String email);
    
    // Búsqueda flexible para el Login (acepta username O email sin importar mayúsculas)
    Optional<UserEntity> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);
}
