package com.learncode_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.learncode_backend.dto.PlanDTO;
import com.learncode_backend.service.PlanService;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @GetMapping
    public List<PlanDTO> listPlans() {
        return service.getActivePlans();
    }
}

