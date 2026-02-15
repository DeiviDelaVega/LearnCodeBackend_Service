package com.learncode_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learncode_backend.repository.PlanRepository;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    
    @Autowired
    private PlanRepository planRepo;

    @PostMapping("/checkout")
    public Map<String, String> createCheckout(
        @RequestParam String planCode,
        @AuthenticationPrincipal Jwt jwt
    ) throws Exception {

        String userId = jwt.getSubject(); // sub

        String priceId = getPriceId(planCode);

        SessionCreateParams params =
            SessionCreateParams.builder()

                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)

                .setSuccessUrl(
                    "http://localhost:4200/client/home?success=true"
                )

                .setCancelUrl(
                    "http://localhost:4200/client/plans?canceled=true"
                )

                // Metadata real
                .putMetadata("userId", userId)
                .putMetadata("planCode", planCode)

                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setPrice(priceId)
                        .setQuantity(1L)
                        .build()
                )

                .build();

        Session session = Session.create(params);

        return Map.of("url", session.getUrl());
    }

    private String getPriceId(String plan) {

        return planRepo.findByCode(plan)
            .orElseThrow(() -> new RuntimeException("Plan no existe"))
            .getStripePriceId();
    }
}

