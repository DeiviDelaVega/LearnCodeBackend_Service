package com.learncode_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentDTO {

    private UUID id;
    private String fullName;
    private String email;
    private String photo;
    private String planCode;
    private Double amount;
    private String status;
    private LocalDateTime createdAt;

    public PaymentDTO(
        UUID id,
        String fullName,
        String email,
        String photo,
        String planCode,
        Double amount,
        String status,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.photo = photo;
        this.planCode = planCode;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public PaymentDTO() {
			}

	// getters
    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhoto() { return photo; }
    public String getPlanCode() { return planCode; }
    public Double getAmount() { return amount; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
