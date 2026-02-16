package com.learncode_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {

            Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.getCredentials() instanceof Jwt jwt) {

                template.header(
                    "Authorization",
                    "Bearer " + jwt.getTokenValue()
                );
            }
        };
    }
}
