package com.learncode_backend.service;

import org.springframework.security.oauth2.jwt.Jwt;

import com.learncode_backend.model.User;

public interface UserService {
    User getOrCreateUser(Jwt jwt);
}