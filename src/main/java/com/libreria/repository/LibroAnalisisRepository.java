package com.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.model.LibroAnalisis;

@Repository
public interface LibroAnalisisRepository  extends JpaRepository<LibroAnalisis, Long>{

}
