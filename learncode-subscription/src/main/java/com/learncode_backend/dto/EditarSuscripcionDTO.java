package com.learncode_backend.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public class EditarSuscripcionDTO {
	@NotNull(message = "El ID es obligatorio")
    private UUID id;
	
    @NotBlank(message = "El nombre es obligatorio")
    private String fullName; 

    private String photo;
    
    @NotBlank(message = "El plan es obligatorio")
    @Pattern(
    	regexp = "FREE|ORO|DIAMANTE|PLATINO",
    	message = "El plan debe ser FREE, ORO, DIAMANTE, PLATINO"
    )
    private String plan;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(
    	regexp = "ACTIVE|CANCELED|EXPIRED",
    	message = "El estado debe ser ACTIVE, CANCELED, EXPIRED"
    )
    private String status;
    
	public EditarSuscripcionDTO(@NotNull(message = "El ID es obligatorio") UUID id,
			@NotBlank(message = "El nombre es obligatorio") String fullName, String photo,
			@NotBlank(message = "El plan es obligatorio") String plan,
			@NotBlank(message = "El estado es obligatorio") String status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.photo = photo;
		this.plan = plan;
		this.status = status;
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
}
