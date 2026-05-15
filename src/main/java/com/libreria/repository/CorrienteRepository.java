package com.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libreria.model.CorrienteLiteraria;

public interface CorrienteRepository extends JpaRepository<CorrienteLiteraria , Long> {

}
