package com.learncode_backend.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learncode_backend.client.AuthClient;
import com.learncode_backend.dto.SubscriptionDTO;
import com.learncode_backend.dto.UserDTO;
import com.learncode_backend.model.Subscription;
import com.learncode_backend.service.SubscriptionService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService service;
    
    private final AuthClient authClient;

    public SubscriptionController(
        SubscriptionService service,
        AuthClient authClient
    ) {
        this.service = service;
		this.authClient = authClient;
    }

   
 // ver mi plan
    @GetMapping("/me")
    public SubscriptionDTO mySubscription(
        @AuthenticationPrincipal Jwt jwt
    ) {

        UserDTO user = authClient.getMe();

        UUID userId = user.getId();

        Subscription sub =
            service.getSubscription(userId);

        if (sub == null || !"ACTIVE".equals(sub.getStatus())) {
            return new SubscriptionDTO(
                null,
                "FREE",
                "ACTIVE",
                null,
                null
            );
        }

        return new SubscriptionDTO(
            sub.getId(),
            sub.getPlanCode(),
            sub.getStatus(),
            sub.getStartAt(),
            sub.getEndAt()
        );
    }


    @PostMapping("/cancel")
    public void cancelSubscription(
        @AuthenticationPrincipal Jwt jwt
    ) {

        UserDTO user = authClient.getMe();

        UUID userId = user.getId();

        service.cancelSubscription(userId);
    }

}
