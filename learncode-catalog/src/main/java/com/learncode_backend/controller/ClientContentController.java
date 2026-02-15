package com.learncode_backend.controller;

import com.learncode_backend.dto.ModuleDTO;
import com.learncode_backend.service.ClientContentService;
import org.springframework.http.ResponseEntity; // Faltaba
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // Faltaba
import java.util.UUID;

@RestController
@RequestMapping("/api/client/content")
public class ClientContentController {

    private final ClientContentService clientService;

    public ClientContentController(ClientContentService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/complete")
    public void complete(
        @AuthenticationPrincipal Jwt jwt,
        @RequestParam UUID moduleId
    ) {

        String userId = jwt.getSubject(); // sub

        clientService.markModuleAsCompleted(
            UUID.fromString(userId),
            moduleId
        );
    }

    @GetMapping("/course/{courseId}")
    public List<ModuleDTO> getContent(
        @PathVariable UUID courseId
    ) {
        return clientService.getModulesForStudent(courseId);
    }

    @GetMapping("/course/{courseId}/progress")
    public List<UUID> getProgress(
        @AuthenticationPrincipal Jwt jwt,
        @PathVariable UUID courseId
    ) {

        String userId = jwt.getSubject();

        return clientService.getCompletedModuleIds(
            UUID.fromString(userId),
            courseId
        );
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<Map<String, String>> getFile(
        @PathVariable UUID fileId
    ) {
        return clientService.getFileContent(fileId);
    }
}
