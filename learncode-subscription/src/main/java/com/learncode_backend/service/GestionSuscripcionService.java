package com.learncode_backend.service;

import com.learncode_backend.dto.EditarSuscripcionDTO;
import com.learncode_backend.dto.ListarSuscripcionDTO;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GestionSuscripcionService {
	Page<ListarSuscripcionDTO> listar(
            String plan,
            String status,
            Pageable pageable
    );
	
	EditarSuscripcionDTO obtenerPorId(UUID id);
	
	void editarSuscripcion(EditarSuscripcionDTO dto);
}
