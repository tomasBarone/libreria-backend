package com.libreria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.model.BibliotecaItem;

@Repository
public interface BibliotecaRepository extends JpaRepository<BibliotecaItem, Long> {

	// obtener todos los guardados del usuario (para la vista "mis lecturas")
	List<BibliotecaItem> findByUsuarioId(Long usuarioId);
	
	// saber si el usuario ya guardo este libro (para pintar el boton activo/inactivo)
	boolean existsByUsuarioIdAndLibroId(Long usuarioId, Long libroId);
	
	// eliminar de guardados
	Optional<BibliotecaItem> findByUsuarioIdAndLibroId(Long usuarioId, Long libroId);
}
