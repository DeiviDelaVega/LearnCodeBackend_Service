package com.learncode_backend.controller;

import com.learncode_backend.dto.CreateFileDTO;
import com.learncode_backend.dto.ModuleDTO;
import com.learncode_backend.model.CourseModule;
import com.learncode_backend.model.ModuleFile;
import com.learncode_backend.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    // 1. Obtener estructura (Módulos + Lista de archivos) de un curso
    @GetMapping("/course/{courseId}")
    public List<ModuleDTO> getByCourse(@PathVariable UUID courseId) {
        return contentService.getModulesByCourse(courseId);
    }

    // 2. Crear Módulo
    @PostMapping("/module")
    public CourseModule createModule(@RequestBody Map<String, Object> body) {
        String courseIdStr = (String) body.get("courseId");
        String title = (String) body.get("title");
        Integer order = (Integer) body.get("order");

        return contentService.createModule(UUID.fromString(courseIdStr), title, order);
    }

    // 3. Eliminar Módulo
    @DeleteMapping("/module/{id}")
    public void deleteModule(@PathVariable UUID id) {
        contentService.deleteModule(id);
    }

    // 4. Subir Archivo (Base64)
    @PostMapping("/file")
    public void uploadFile(@RequestBody CreateFileDTO dto) {
        contentService.uploadFile(dto);
    }

    // 5. Descargar/Ver Archivo (Recuperar el Base64)
    @GetMapping("/file/{id}")
    public ResponseEntity<Map<String, String>> getFile(@PathVariable UUID id) {
        ModuleFile file = contentService.getFileContent(id);
        
        // Retornamos el Base64 para que el front lo convierta en Blob y lo descargue
        return ResponseEntity.ok(Map.of(
            "fileName", file.getFileName(),
            "mimeType", file.getMimeType(),
            "base64", file.getBase64Content()
        ));
    }

    
    @PutMapping("/module/{id}")
    public CourseModule updateModule(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        String newTitle = body.get("title");
        return contentService.updateModule(id, newTitle);
    }
    
    // 6. Eliminar Archivo
    @DeleteMapping("/file/{id}")
    public void deleteFile(@PathVariable UUID id) {
        contentService.deleteFile(id);
    }
}