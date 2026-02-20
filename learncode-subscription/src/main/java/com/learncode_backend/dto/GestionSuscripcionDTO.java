package com.learncode_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GestionSuscripcionDTO {
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
	@Pattern(
		regexp = "FREE|ORO|DIAMANTE|PLATINO",
		message = "El plan debe ser FREE, ORO, DIAMANTE, PLATINO"
	)
    private String plan;
	
    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(
    	regexp = "ACTIVE|CANCELED|EXPIRED",
    	message = "El estado debe ser ACTIVE, CANCELED, EXPIRED"
    )
    private String status;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    private LocalDateTime startAt;
    
    @Future(message = "La fecha de fin debe ser una fecha futura")
    private LocalDateTime endAt;
    
    private LocalDateTime updatedAt;
}