package com.learncode_backend.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learncode_backend.client.AuthClient;
import com.learncode_backend.dto.EditarSuscripcionDTO;
import com.learncode_backend.dto.ListarSuscripcionDTO;
import com.learncode_backend.dto.UserDTO;
import com.learncode_backend.model.Subscription;
import com.learncode_backend.repository.GestionSuscripcionRepository;
import com.learncode_backend.service.GestionSuscripcionService;
import jakarta.transaction.Transactional;

@Service
public class GestionSuscripcionServiceImpl implements GestionSuscripcionService {
	@Autowired
    private GestionSuscripcionRepository repository;
	
	@Autowired
	private AuthClient authClient;
	
	@Override
	@Transactional
	public Page<ListarSuscripcionDTO> listar(
	    String plan,
	    String status,
	    Pageable pageable
	) {
	    Page<Subscription> page = repository.listarSuscripciones(plan, status, pageable);

	    return page.map(sub -> {

	        UserDTO user = authClient.getUser(sub.getUserId());

	        return new ListarSuscripcionDTO(
	            sub.getId(),
	            user.getFullName(),
	            user.getEmail(),
	            user.getPhoto() != null
	                ? user.getPhoto()
	                : "https://ui-avatars.com/api/?name=" +
	                    user.getFullName().replace(" ", "+"),
	            sub.getPlanCode(),
	            sub.getStatus(),
	            sub.getStartAt(),
	            sub.getEndAt(),
	            sub.getUpdatedAt()
	        );
	    });
	}

	@Override
	@Transactional
	public void editarSuscripcion(EditarSuscripcionDTO dto) {
		if ("ACTIVE".equals(dto.getStatus())) {

	        LocalDateTime nuevoFin = LocalDateTime.now()
	                .plusMonths(1)
	                .plusDays(2);

	        repository.activarSuscripcion(
	            dto.getId(),
	            dto.getPlan(),
	            nuevoFin
	        );

	    } else if ("CANCELED".equals(dto.getStatus())) {

	        repository.cancelarSuscripcion(
	            dto.getId(),
	            dto.getPlan()
	        );

	    } else {

	        repository.actualizarSuscripcion(
	            dto.getId(),
	            dto.getPlan(),
	            dto.getStatus()
	        );
	    }
	}

	@Override
	public EditarSuscripcionDTO obtenerPorId(UUID id) {
		
		Subscription sub = repository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Suscripción no existe"));

		    UserDTO user = authClient.getUser(sub.getUserId());

		    return new EditarSuscripcionDTO(
		        sub.getId(),
		        user.getFullName(),
		        user.getPhoto(),
		        sub.getPlanCode(),
		        sub.getStatus()
		    );
	}
}
