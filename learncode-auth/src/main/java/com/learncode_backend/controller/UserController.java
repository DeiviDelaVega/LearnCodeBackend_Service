package com.learncode_backend.controller;

import com.learncode_backend.model.User;
import com.learncode_backend.service.GestionClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {

    @Autowired
    private GestionClienteService service;

    @GetMapping("/id/{email}")
    public ResponseEntity<UUID> obtenerIdPorEmail(@PathVariable String email) {
        User user = service.obtenerCliente(email); 
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getId());
    }
}