package com.learncode_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdminCourseDTO(
        UUID id,
        String title,
        String subtitle,
        String description,
        String iconUrl,
        String coverUrl,
        boolean isFree,
        String requiredPlanCode,
        boolean isPublished,
        LocalDateTime createdAt
) {}