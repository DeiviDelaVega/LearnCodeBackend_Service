package com.learncode_backend.dto;

import jakarta.validation.constraints.*;

public class EditarClienteDTO {
	@NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String fullName;

    @NotBlank(message = "El rol es obligatorio")
    @Pattern(
        regexp = "USER|ADMIN",
        message = "El rol debe ser USER o ADMIN"
    )
    private String role;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(
        regexp = "ACTIVE|BLOCKED",
        message = "Estado inválido debe ser ACTIVE o BLOCKED "
    )
    private String status;
    
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
}
