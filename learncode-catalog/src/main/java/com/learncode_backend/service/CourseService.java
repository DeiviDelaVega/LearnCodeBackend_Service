package com.learncode_backend.service;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.learncode_backend.dto.ClientCourseDTO;
import com.learncode_backend.model.Course;

public interface CourseService {
	
	// ADMIN
	Course createCourse(Course course);
	List<Course> findAll();
	List<Course> findAll(String title);
	Course findById(UUID id);
	Course updateCourse(UUID id, Course course);
	void deleteCourse(UUID id);
	
	Page<Course> findPaged(Pageable pageable, String title, Boolean published);
	List<Course> findPublished(String title);
	
	//CLIENT
	List<ClientCourseDTO> listPublished();
	ClientCourseDTO getById(UUID id);
	
}
