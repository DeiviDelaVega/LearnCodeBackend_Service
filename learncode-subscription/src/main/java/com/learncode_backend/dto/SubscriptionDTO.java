package com.learncode_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionDTO {

    private UUID id;
    private String planCode;
    private String status;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    
    public SubscriptionDTO() {
    }

    public SubscriptionDTO(
            UUID id,
            String planCode,
            String status,
            LocalDateTime startAt,
            LocalDateTime endAt
    ) {
        this.id = id;
        this.planCode = planCode;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
}