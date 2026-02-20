package com.learncode_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscriptionDTO {
    private UUID id;
    private String planCode;
    private String status;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}