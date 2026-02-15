package com.learncode_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.*;

public class ListarSuscripcionDTO {
	@NotNull(message = "El ID no puede ser nulo")
	private UUID id;

	@NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String fullName;
    
	@NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un correo electrónico válido")
	private String email;
	
	@Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "La foto debe ser una URL válida")
    private String photo;

	@NotBlank(message = "El plan no puede estar vacío")
    private String plan;
	
    @NotBlank(message = "El estado no puede estar vacío")
    private String status;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    private LocalDateTime startAt;
    
    @Future(message = "La fecha de fin debe ser una fecha futura")
    private LocalDateTime endAt;
    
    private LocalDateTime updatedAt;
    
	public ListarSuscripcionDTO(UUID id, String fullName, String email, String photo, String plan, String status,
			LocalDateTime startAt, LocalDateTime endAt, LocalDateTime updatedAt) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.photo = photo;
		this.plan = plan;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
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
