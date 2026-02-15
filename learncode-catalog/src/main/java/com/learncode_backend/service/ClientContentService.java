package com.learncode_backend.service;

import com.learncode_backend.dto.FileDTO;
import com.learncode_backend.dto.ModuleDTO;
import com.learncode_backend.model.CourseModule;
import com.learncode_backend.model.ModuleFile;
import com.learncode_backend.model.StudentProgress;
import com.learncode_backend.repository.CourseModuleRepository;
import com.learncode_backend.repository.ModuleFileRepository; // Importante
import com.learncode_backend.repository.StudentProgressRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map; // Faltaba esto
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientContentService {

	private final CourseModuleRepository moduleRepo;
    private final StudentProgressRepository progressRepo;
    private final ModuleFileRepository fileRepo;

    public ClientContentService(
        CourseModuleRepository moduleRepo,
        StudentProgressRepository progressRepo,
        ModuleFileRepository fileRepo
    ) {
        this.moduleRepo = moduleRepo;
        this.progressRepo = progressRepo;
        this.fileRepo = fileRepo;
    }

    public List<ModuleDTO> getModulesForStudent(UUID courseId) {
        return moduleRepo.findByCourseIdOrderByOrderAsc(courseId).stream()
            .map(m -> new ModuleDTO(
                m.getId(),
                m.getOrder(),
                m.getTitle(),
                m.getFiles().stream()
                    .map(f -> new FileDTO(f.getId(), f.getFileName(), f.getMimeType()))
                    .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
    }

    public List<UUID> getCompletedModuleIds(UUID userId, UUID courseId) {
        return progressRepo.findByUserIdAndCourseId(userId, courseId).stream()
                .map(StudentProgress::getModuleId)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markModuleAsCompleted(UUID userId, UUID moduleId) {

        if (progressRepo.existsByUserIdAndModuleId(userId, moduleId)) {
            return;
        }

        CourseModule module = moduleRepo.findById(moduleId).orElseThrow();

        StudentProgress progress = new StudentProgress();

        progress.setUserId(userId);
        progress.setModuleId(moduleId);
        progress.setCourseId(module.getCourseId());

        progressRepo.save(progress);
    }
    
    // Método para servir el archivo
    public ResponseEntity<Map<String, String>> getFileContent(UUID fileId) {
        ModuleFile file = fileRepo.findById(fileId)
            .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
            
        return ResponseEntity.ok(Map.of(
            "fileName", file.getFileName(),
            "mimeType", file.getMimeType(),
            "base64", file.getBase64Content()
        ));
    }
}