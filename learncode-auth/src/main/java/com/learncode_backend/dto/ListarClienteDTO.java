package com.learncode_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ListarClienteDTO {
	private UUID id;
    private String email;
    private String fullName;
    private String photo;
    private String role;
    private String status;
    private LocalDateTime createdAt;
    
	public ListarClienteDTO(UUID id, String email, String fullName, String photo, String role, String status,
			LocalDateTime createdAt) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.photo = photo;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
