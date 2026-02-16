package com.learncode_backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learncode_backend.dto.ClientCourseDTO;
import com.learncode_backend.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

	// Filtros
	List<Course> findByTitle(String title);

	List<Course> findByIsPublishedTrue();

	List<Course> findByIsPublishedTrueAndTitle(String title);

	// Conteo
	@Query("SELECT COUNT(c) FROM Course c WHERE c.isPublished = true")
	long countPublished();

	@Query("SELECT c FROM Course c WHERE " + "(:title IS NULL OR CAST(c.title AS string) ILIKE %:title%) AND "
			+ "(:published IS NULL OR c.isPublished = :published)")
	Page<Course> findWithFilters(@Param("title") String title, @Param("published") Boolean published,
			Pageable pageable);

	@Query("""
		    SELECT new com.learncode_backend.dto.ClientCourseDTO(
		        c.id,
		        c.title,
		        c.subtitle,
		        c.iconUrl,
		        c.coverUrl,
		        c.isFree,
		        c.requiredPlanCode,
		        COUNT(DISTINCT m),
		        COUNT(DISTINCT f)
		    )
		    FROM Course c
		    LEFT JOIN c.modules m
		    LEFT JOIN m.files f
		    WHERE c.isPublished = true
		    GROUP BY c.id, c.title, c.subtitle, c.iconUrl, c.coverUrl, c.isFree, c.requiredPlanCode
		""")
		List<ClientCourseDTO> findAllIsPublishedWithCounts();


	@Query("SELECT new com.learncode_backend.dto.ClientCourseDTO("
			+ "c.id, c.title, c.subtitle, c.iconUrl, c.coverUrl, c.isFree, c.requiredPlanCode, "
			+ "(SELECT COUNT(m) FROM CourseModule m WHERE m.courseId = c.id), "
			+ "(SELECT COUNT(f) FROM ModuleFile f WHERE f.module.courseId = c.id)) "
			+ "FROM Course c WHERE c.id = :id AND c.isPublished = true")
	ClientCourseDTO findPublishedByIdWithCounts(@Param("id") UUID id);

}
