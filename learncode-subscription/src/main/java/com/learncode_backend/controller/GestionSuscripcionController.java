package com.learncode_backend.controller;

import com.learncode_backend.dto.EditarSuscripcionDTO;
import com.learncode_backend.dto.ListarSuscripcionDTO;
import com.learncode_backend.service.GestionSuscripcionService;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/gestionSuscripcion")
public class GestionSuscripcionController {
	@Autowired
    private GestionSuscripcionService service;

    @GetMapping
    public ResponseEntity<Page<ListarSuscripcionDTO>> listar(
            @RequestParam(defaultValue = "TODO") String plan,
            @RequestParam(defaultValue = "TODO") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        return ResponseEntity.ok(service.listar(plan, status, pageable));
    }
    
    @PutMapping
    public ResponseEntity<Void> editar(
            @RequestBody @Valid EditarSuscripcionDTO dto
    ) {
        service.editarSuscripcion(dto);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EditarSuscripcionDTO> obtenerPorId(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
}
