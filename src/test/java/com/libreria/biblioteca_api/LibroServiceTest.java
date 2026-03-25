package com.libreria.biblioteca_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.libreria.dto.LibroResponseDTO;
import com.libreria.exception.GlobalExceptionHandler;
import com.libreria.model.Categoria;
import com.libreria.model.Libro;
import com.libreria.repository.LibroRepository;
import com.libreria.service.LibroService;

@ExtendWith(MockitoExtension.class) // Habilita el uso de Mocks
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository; // Simulamos el repositorio

    @InjectMocks
    private LibroService libroService; // Spring inyecta el simulador aquí

    @Test
    void cuandoObtenerPorId_siExiste_debeRetornarLibroDTO() {
   
        Categoria catFalsa = new Categoria();
        catFalsa.setNombre("Filosofia");

      
        Libro libroFalso = new Libro();
        libroFalso.setId(1L);
        libroFalso.setTitulo("Test Book");
        libroFalso.setCategoria(catFalsa); 

        // Configuramos el Mockito
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libroFalso));

        // 2. ACT
        LibroResponseDTO resultado = libroService.obtenerPorId(1L);

        // 3. ASSERT
        assertNotNull(resultado);
        assertEquals("Filosofia", resultado.getCategoriaNombre());
    }
    
    @Test
    void cuandoObtenerPorId_siNoExiste_debeLanzarExcepcion() {
        // 1. ARRANGE
        // Simulamos que el repo devuelve un Optional VACÍO
        when(libroRepository.findById(999L)).thenReturn(Optional.empty());

        // 2. ACT & ASSERT (En JUnit 5 se puede hacer todo junto)
        assertThrows(RuntimeException.class, () -> {
            libroService.obtenerPorId(999L);
        });
        
        // Verificamos que nunca se intentó mapear nada porque falló antes
        verify(libroRepository, times(1)).findById(999L);
    }
}