package com.learncode_backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learncode_backend.dto.ClientCourseDTO;
import com.learncode_backend.model.Course;
import com.learncode_backend.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public List<ClientCourseDTO> listPublished() throws Exception{
		List<ClientCourseDTO> result = courseService.listPublished();
		return result;
	}

	@GetMapping("/{id}")
	public ClientCourseDTO getById(@PathVariable UUID id) throws Exception {
		return courseService.getById(id);
	}
}
