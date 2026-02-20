package com.learncode_backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;


@FeignClient(name = "AUTH-SERVICE") 
public interface AuthClient {

    @GetMapping("/api/admin/gestionCliente/id/{email}") 
    UUID getUserIdByEmail(@PathVariable("email") String email);
}