package com.learncode_backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.learncode_backend.dto.AdminCourseDTO;
import com.learncode_backend.model.Course;
import com.learncode_backend.service.CloudinaryService;
import com.learncode_backend.service.CourseService;

@RestController
@RequestMapping("/api/admin/courses")
public class CourseAdminController {

    private final CourseService courseService;
    private final CloudinaryService cloudinaryService;

    public CourseAdminController(CourseService courseService, CloudinaryService cloudinaryService) {
        this.courseService = courseService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/paged")
    public Page<AdminCourseDTO> findPaged(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) Boolean published) {
        Pageable pageable = PageRequest.of(page, size);
        return courseService.findPaged(pageable, title, published)
                .map(this::mapToDTO);
    }

    @PostMapping
    public AdminCourseDTO create(@RequestBody Course course) {
        Course saved = courseService.createCourse(course);
        return mapToDTO(saved);
    }

    @GetMapping
    public List<AdminCourseDTO> listAll() {
        return courseService.findAll().stream().map(this::mapToDTO).toList();
    }

    @GetMapping("/{id}")
    public AdminCourseDTO getById(@PathVariable UUID id) {
        Course c = courseService.findById(id);
        return mapToDTO(c);
    }

    @PutMapping("/{id}")
    public AdminCourseDTO update(@PathVariable UUID id, @RequestBody Course course) {
        Course updated = courseService.updateCourse(id, course);
        return mapToDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        courseService.deleteCourse(id);
    }

    // ✨ Nuevo endpoint para subir icono
    @PostMapping("/{id}/upload/icon")
    public AdminCourseDTO uploadIcon(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        String url = cloudinaryService.uploadImage(file);
        Course course = courseService.findById(id);
        course.setIconUrl(url);
        Course updated = courseService.updateCourse(id, course);
        return mapToDTO(updated);
    }

    // ✨ Nuevo endpoint para subir cover
    @PostMapping("/{id}/upload/cover")
    public AdminCourseDTO uploadCover(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        String url = cloudinaryService.uploadImage(file);
        Course course = courseService.findById(id);
        course.setCoverUrl(url);
        Course updated = courseService.updateCourse(id, course);
        return mapToDTO(updated);
    }

    private AdminCourseDTO mapToDTO(Course c) {
        return new AdminCourseDTO(
                c.getId(),
                c.getTitle(),
                c.getSubtitle(),
                c.getDescription(),
                c.getIconUrl(),
                c.getCoverUrl(),
                c.isFree(),
                c.getRequiredPlanCode(),
                c.isPublished(),
                c.getCreatedAt()
        );
    }
}
