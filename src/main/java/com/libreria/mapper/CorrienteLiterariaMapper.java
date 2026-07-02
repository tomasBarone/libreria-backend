package com.libreria.mapper;

import org.springframework.stereotype.Component;

import com.libreria.dto.CorrienteResponseDTO;
import com.libreria.model.CorrienteLiteraria;

@Component
public class CorrienteLiterariaMapper {
	/**
     * Transforma una Entidad JPA (con conexiones pesadas) en un DTO liviano (solo ID y Nombre)
     */
    public CorrienteResponseDTO toResponseDTO(CorrienteLiteraria corriente) {
        // Control de seguridad por si la base de datos devuelve un null
        if (corriente == null) {
            return null;
        }

        // 1. Instanciamos el objeto vacío
        CorrienteResponseDTO dto = new CorrienteResponseDTO();

        // 2. Extraemos los datos de la entidad y se los seteamos al DTO
        dto.setId(corriente.getId());
        dto.setNombre(corriente.getNombre());
        dto.setPeriodo(corriente.getPeriodo());
        dto.setDescripcion(corriente.getDescripcion());
        dto.setCaracteristicas(corriente.getCaracteristicas());
        dto.setFundamentos(corriente.getFundamentos());

        // 3. Retornamos el objeto transformado
        return dto;
    }
}
