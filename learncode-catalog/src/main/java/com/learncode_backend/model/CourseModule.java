package com.learncode_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "course_modules", schema = "catalog_schema")
public class CourseModule {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "course_id", nullable = false)
    private UUID courseId;

    @Column(name = "module_order", nullable = false)
    private Integer order; // 1, 2, 3...

    @Column(nullable = false, length = 150)
    private String title;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Relación para traer los archivos automáticamente
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("uploadedAt ASC")
    private List<ModuleFile> files;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getCourseId() { return courseId; }
    public void setCourseId(UUID courseId) { this.courseId = courseId; }
    public Integer getOrder() { return order; }
    public void setOrder(Integer order) { this.order = order; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<ModuleFile> getFiles() { return files; }
    public void setFiles(List<ModuleFile> files) { this.files = files; }
}