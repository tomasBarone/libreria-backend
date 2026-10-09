package com.libreria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.dto.LibroResponseDTO;
import com.libreria.mapper.LibroMapper;
import com.libreria.model.BibliotecaItem;
import com.libreria.model.Libro;
import com.libreria.model.UserEntity;
import com.libreria.repository.BibliotecaRepository;
import com.libreria.repository.LibroRepository;
import com.libreria.repository.UserRepository;

@Service
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepo;
    private final LibroRepository libroRepository;
    private final UserRepository userRepository;
    private final LibroMapper libroMapper; // Inyección directa del mapper de MapStruct

    public BibliotecaService(BibliotecaRepository bibliotecaRepo, 
                             LibroRepository libroRepository,
                             UserRepository userRepository,
                             LibroMapper libroMapper) {
        this.bibliotecaRepo = bibliotecaRepo;
        this.libroRepository = libroRepository;
        this.userRepository = userRepository;
        this.libroMapper = libroMapper;
    }

    // Retorna directamente la lista de Libros formateados a LibroResponseDTO
    @Transactional(readOnly = true)
    public List<LibroResponseDTO> obtenerLecturas(Long usuarioId) {
        return bibliotecaRepo.findByUsuarioId(usuarioId).stream()
                .map(item -> libroMapper.toResponseDTO(item.getLibro())) // MapStruct en 1 sola línea
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean estaGuardado(Long usuarioId, Long libroId) {
        return bibliotecaRepo.existsByUsuarioIdAndLibroId(usuarioId, libroId);
    }

    @Transactional
    public boolean alternarGuardado(Long usuarioId, Long libroId) {
        Optional<BibliotecaItem> itemExistente = bibliotecaRepo.findByUsuarioIdAndLibroId(usuarioId, libroId);

        if (itemExistente.isPresent()) {
            bibliotecaRepo.delete(itemExistente.get());
            return false;
        } else {
            UserEntity usuario = userRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
            Libro libro = libroRepository.findById(libroId)
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + libroId));

            BibliotecaItem item = new BibliotecaItem(usuario, libro);
            bibliotecaRepo.save(item);
            return true;
        }
    }
}