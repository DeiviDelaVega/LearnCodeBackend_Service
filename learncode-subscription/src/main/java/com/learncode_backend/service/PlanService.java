package com.learncode_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learncode_backend.dto.PlanDTO;
import com.learncode_backend.model.Plan;
import com.learncode_backend.repository.PlanRepository;

@Service
public class PlanService {

    private final PlanRepository repo;

    public PlanService(PlanRepository repo) {
        this.repo = repo;
    }

    public List<PlanDTO> getActivePlans() {

        return repo.findAll()
            .stream()
            .filter(p -> Boolean.TRUE.equals(p.getIsActive()))
            .map(this::toDTO)
            .toList();
    }

    private PlanDTO toDTO(Plan p) {

        return new PlanDTO(
            p.getCode(),
            p.getName(),
            p.getDescription(),
            p.getPriceCents() / 100.0,
            p.getDurationDays()
        );
    }
}

