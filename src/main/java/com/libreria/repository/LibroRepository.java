package com.libreria.repository;

import com.libreria.model.Libro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	List<Libro> findByAutorContainingIgnoreCase(String autor);
	List<Libro> findByTituloContainingIgnoreCase(String titulo);
	List<Libro> findByanioPublicacion(Integer anioPublicacion);
	//List<Libro> findByCategoriaNombreAndAnioPublicacionLessThan(String nombreCat, Integer anio);
	
	
	@Query("SELECT l FROM Libro l WHERE " +
		       "(:inicio IS NULL OR l.anioPublicacion >= :inicio) AND " +
		       "(:fin IS NULL OR l.anioPublicacion <= :fin)")
		Page<Libro> filtrarLibrosPro( @Param("inicio") Integer inicio, @Param("fin") Integer fin, Pageable pageable);
	
	List<Libro> findByCorrienteId(Long movimientoId);
	
	// Compara la query contra el TÍTULO O contra el NOMBRE DEL AUTOR en una sola consulta a la BD
    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Libro> buscarPorTituloOAutor(@Param("query") String query);
}
