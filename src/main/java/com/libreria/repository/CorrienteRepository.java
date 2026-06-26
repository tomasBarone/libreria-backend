package com.libreria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.libreria.model.CorrienteLiteraria;

public interface CorrienteRepository extends JpaRepository<CorrienteLiteraria , Long> {
	

}
