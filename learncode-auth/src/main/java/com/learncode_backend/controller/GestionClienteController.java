package com.learncode_backend.controller;

import com.learncode_backend.dto.EditarClienteDTO;
import com.learncode_backend.dto.ListarClienteDTO;
import com.learncode_backend.service.GestionClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/admin/gestionCliente")
public class GestionClienteController {
	@Autowired
    private GestionClienteService service;

    @GetMapping
    public Page<ListarClienteDTO> listarClientes(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return service.listarClientes(search, status, role, pageable);
    }
    
    @GetMapping("/{email}")
    public ListarClienteDTO obtenerCliente(@PathVariable String email) {
        email = URLDecoder.decode(email, StandardCharsets.UTF_8)
                .trim()
                .toLowerCase();

        return service.obtenerCliente(email);
    }
    
    @PutMapping("/{email}")
    public ListarClienteDTO editarCliente(
            @PathVariable String email,
            @Valid @RequestBody EditarClienteDTO data
    ) {
        email = URLDecoder.decode(email, StandardCharsets.UTF_8)
                .trim()
                .toLowerCase();

        return service.editarCliente(email, data);
    }
}
