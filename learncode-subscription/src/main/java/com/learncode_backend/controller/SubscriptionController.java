package com.learncode_backend.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learncode_backend.dto.SubscriptionDTO;
import com.learncode_backend.model.Subscription;
import com.learncode_backend.service.SubscriptionService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(
        SubscriptionService service
    ) {
        this.service = service;
    }

   
 // ver mi plan
    @GetMapping("/me")
    public SubscriptionDTO mySubscription(
        @AuthenticationPrincipal Jwt jwt
    ) {

        String userId = jwt.getSubject();

        Subscription sub =
            service.getSubscription(UUID.fromString(userId));

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

        String userId = jwt.getSubject();

        service.cancelSubscription(
            UUID.fromString(userId)
        );
    }


}
