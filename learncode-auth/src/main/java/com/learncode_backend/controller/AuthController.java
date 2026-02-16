package com.learncode_backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learncode_backend.dto.UserDTO;
import com.learncode_backend.model.User;
import com.learncode_backend.repository.UserRepository;
import com.learncode_backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserDTO me(@AuthenticationPrincipal Jwt jwt) {

        User user = userService.getOrCreateUser(jwt);

        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoto(),
                user.getRole(),     
                user.getStatus() 
            );
    }
    
    @GetMapping("/internal/user/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {

        User user = userRepository.findById(id)
            .orElseThrow();

        return new UserDTO(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhoto(),
            user.getRole(),   
            user.getStatus()  
        );
    }

}
