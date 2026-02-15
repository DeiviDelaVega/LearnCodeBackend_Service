package com.learncode_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions", schema = "subscription_schema")
public class Subscription {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "plan_code", nullable = false)
    private String planCode; // FREE, BASIC, PREMIUM, PRO

    @Column(nullable = false)
    private String status; // ACTIVE, CANCELED, EXPIRED

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = true)
    private LocalDateTime endAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public Subscription() {
		super();
	}
	
	public Subscription(UUID id, UUID userId, String planCode, String status, LocalDateTime startAt,
			LocalDateTime endAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.planCode = planCode;
		this.status = status;
		this.startAt = startAt;
		this.endAt = endAt;
		this.updatedAt = updatedAt;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
