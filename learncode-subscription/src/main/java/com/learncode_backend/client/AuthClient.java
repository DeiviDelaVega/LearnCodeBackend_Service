package com.learncode_backend.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learncode_backend.dto.UserDTO;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthClient {

    @GetMapping("/api/auth/internal/user/{id}")
    UserDTO getUser(@PathVariable("id") UUID id);
}
