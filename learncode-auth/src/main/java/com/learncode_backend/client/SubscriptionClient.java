package com.learncode_backend.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("SUBSCRIPTION-SERVICE")
public interface SubscriptionClient {

    @GetMapping("/api/subscription/daily-income")
    BigDecimal dailyIncome();
}