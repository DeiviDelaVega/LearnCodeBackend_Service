package com.learncode_backend.service;

import com.learncode_backend.client.AuthClient;
import com.learncode_backend.dto.FileDTO;
import com.learncode_backend.dto.ModuleDTO;
import com.learncode_backend.model.CourseModule;
import com.learncode_backend.model.ModuleFile;
import com.learncode_backend.model.StudentProgress;
import com.learncode_backend.repository.CourseModuleRepository;
import com.learncode_backend.repository.ModuleFileRepository;
import com.learncode_backend.repository.StudentProgressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientContentService {

    private final CourseModuleRepository moduleRepo;
    private final StudentProgressRepository progressRepo;
    private final ModuleFileRepository fileRepo;
    private final AuthClient authClient;

    public ClientContentService(CourseModuleRepository moduleRepo, 
                                StudentProgressRepository progressRepo, 
                                ModuleFileRepository fileRepo,
                                AuthClient authClient) {
        this.moduleRepo = moduleRepo;
        this.progressRepo = progressRepo;
        this.fileRepo = fileRepo;
        this.authClient = authClient;
    }

    public List<ModuleDTO> getModulesForStudent(UUID courseId) {
        return moduleRepo.findByCourseIdOrderByModuleOrderAsc(courseId).stream()
            .map(m -> new ModuleDTO(
                m.getId(),
                m.getModuleOrder(),
                m.getTitle(),
                m.getFiles().stream()
                    .map(f -> new FileDTO(f.getId(), f.getFileName(), f.getMimeType()))
                    .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
    }

    public List<UUID> getCompletedModuleIds(String email, UUID courseId) {
        UUID userId = authClient.getUserIdByEmail(email);
        return progressRepo.findByUserIdAndCourseId(userId, courseId).stream()
                .map(StudentProgress::getModuleId)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markModuleAsCompleted(String email, UUID moduleId) {
        UUID userId = authClient.getUserIdByEmail(email);
        
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