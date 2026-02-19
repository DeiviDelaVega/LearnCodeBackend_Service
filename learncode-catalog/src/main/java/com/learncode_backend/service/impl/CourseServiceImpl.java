package com.learncode_backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learncode_backend.dto.ClientCourseDTO;
import com.learncode_backend.model.Course;
import com.learncode_backend.repository.CourseRepository;
import com.learncode_backend.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepo;

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepo = courseRepository;
	}

	@Override
	public Course createCourse(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public List<Course> findAll() {
		return courseRepo.findAll();
	}

	@Override
	public List<Course> findAll(String title) {
		if (title == null || title.isBlank()) {
			return courseRepo.findAll();
		}
		return courseRepo.findByTitle(title);
	}

	@Override
	public Course findById(UUID id) {
		return courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
	}

	@Override
	public Course updateCourse(UUID id, Course course) {
		Course courseExiting = findById(id);

		courseExiting.setTitle(course.getTitle());
		courseExiting.setSubtitle(course.getSubtitle());
		courseExiting.setDescription(course.getDescription());
		courseExiting.setIconUrl(course.getIconUrl());
		courseExiting.setCoverUrl(course.getCoverUrl());
		courseExiting.setFree(course.isFree());
		courseExiting.setRequiredPlanCode(course.getRequiredPlanCode());
		courseExiting.setIsPublished(course.isPublished());

		return courseRepo.save(courseExiting);
	}

	@Override
	public void deleteCourse(UUID id) {
		courseRepo.deleteById(id);
	}

	// Filtros
	@Override
	public List<Course> findPublished(String title) {
		if (title == null || title.isBlank()) {
			return courseRepo.findByIsPublishedTrue();
		}
		return courseRepo.findByIsPublishedTrueAndTitle(title);
	}

	@Override
	public Page<Course> findPaged(Pageable pageable, String title, Boolean published) {
		return courseRepo.findWithFilters(title, published, pageable);
	}

	@Override
	public List<ClientCourseDTO> listPublished() {
		return courseRepo.findAllIsPublishedWithCounts();
	}

	@Override
	public ClientCourseDTO getById(UUID id) {
		ClientCourseDTO dto = courseRepo.findPublishedByIdWithCounts(id);

		if (dto == null) {
			throw new RuntimeException("Curso no encontrado");
		}

		return dto;
	}

}
