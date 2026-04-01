package com.libreria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libreria.model.RoleEntity;
import com.libreria.model.RoleEnum;
import com.libreria.model.UserEntity;

public interface RoleRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<RoleEntity> findByRoleName(RoleEnum roleName);

}
