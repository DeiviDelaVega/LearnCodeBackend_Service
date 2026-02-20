package com.learncode_backend.dto;


import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class AdminCourseDTO {
	UUID id;


	@NotBlank(message = "El título es obligatorio")
	@Size(min = 5, max = 150, message = "El título debe tener mínimo 5 caracteres")
	String title;


	@NotBlank(message = "El subtítulo es obligatorio")
	@Size(min = 5, max = 150, message = "El subtítulo debe tener mínimo 5 caracteres")
	String subtitle;


	@NotBlank(message = "La descripción es obligatoria")
	@Size(min = 20, message = "La descripción debe tener mínimo 20 caracteres")
	String description;


	@NotBlank(message = "El icono del curso es obligatorio")
	String iconUrl;


	@NotBlank(message = "El color cover es obligatorio")
	String coverUrl;


	boolean isFree;


	String requiredPlanCode;


	boolean published;


	LocalDateTime createdAt;


	public AdminCourseDTO() {
	}


	public AdminCourseDTO(UUID id,
			@NotBlank(message = "El título es obligatorio") @Size(min = 5, max = 150, message = "El título debe tener mínimo 5 caracteres") String title,
			@NotBlank(message = "El subtítulo es obligatorio") @Size(min = 5, max = 150, message = "El subtítulo debe tener mínimo 5 caracteres") String subtitle,
			@NotBlank(message = "La descripción es obligatoria") @Size(min = 20, message = "La descripción debe tener mínimo 20 caracteres") String description,
			@NotBlank(message = "El icono del curso es obligatorio") String iconUrl,
			@NotBlank(message = "El color cover es obligatorio") String coverUrl, boolean isFree,
			String requiredPlanCode, boolean published, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.iconUrl = iconUrl;
		this.coverUrl = coverUrl;
		this.isFree = isFree;
		this.requiredPlanCode = requiredPlanCode;
		this.published = published;
		this.createdAt = createdAt;
	}


	@AssertTrue(message = "Si el curso no es gratis, debe seleccionar un plan")
	public boolean isPlanValid() {
		if (!isFree) {
			return requiredPlanCode != null && requiredPlanCode.matches("ORO|PLATINO|DIAMANTE");
		}
		return true;
	}


	@AssertTrue(message = "Un curso gratis no debe tener plan requerido")
	public boolean isFreeCourseValid() {
		if (isFree) {
			return requiredPlanCode == null || requiredPlanCode.isBlank();
		}
		return true;
	}


}


