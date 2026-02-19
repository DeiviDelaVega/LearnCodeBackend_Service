package com.learncode_backend.repository;

import com.learncode_backend.model.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GestionClienteRepository extends JpaRepository<User, UUID> {
	@Query("""
		    SELECT u
		    FROM User u
		    WHERE (:search IS NULL OR :search = '' 
		           OR LOWER(u.email) LIKE CONCAT('%', LOWER(:search), '%')
		           OR LOWER(u.fullName) LIKE CONCAT('%', LOWER(:search), '%'))
		      AND (:status IS NULL OR :status = 'ALL' OR u.status = :status)
		      AND UPPER(TRIM(u.role)) = UPPER(TRIM(:role))
		    ORDER BY u.createdAt ASC
		""")
	Page<User> findClientes(
	        @Param("search") String search,
	        @Param("status") String status,
	        @Param("role") String role,
	        Pageable pageable
	);
}
