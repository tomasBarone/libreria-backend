package com.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.libreria.model.GeneroLiterario;

public interface GeneroRepository extends JpaRepository<GeneroLiterario, Long> {

}
