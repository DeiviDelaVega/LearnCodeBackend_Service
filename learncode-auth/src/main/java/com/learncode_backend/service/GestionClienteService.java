package com.learncode_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.learncode_backend.dto.EditarClienteDTO;
import com.learncode_backend.dto.ListarClienteDTO;

public interface GestionClienteService {
	Page<ListarClienteDTO> listarClientes(
            String search,
            String status,
            String role,
            Pageable pageable
    );
	
	ListarClienteDTO obtenerCliente(String email);
	
    ListarClienteDTO editarCliente(String email, EditarClienteDTO data);
}
