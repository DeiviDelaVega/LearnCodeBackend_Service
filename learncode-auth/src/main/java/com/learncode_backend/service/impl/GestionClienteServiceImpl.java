package com.learncode_backend.service.impl;

import com.learncode_backend.dto.EditarClienteDTO;
import com.learncode_backend.dto.ListarClienteDTO;
import com.learncode_backend.model.User;
import com.learncode_backend.repository.GestionClienteRepository;
import com.learncode_backend.repository.UserRepository;
import com.learncode_backend.service.GestionClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GestionClienteServiceImpl implements GestionClienteService{
	@Autowired
    private GestionClienteRepository gestionClienteRepository;

	@Autowired
    private UserRepository userRepository;

	@Override
	public Page<ListarClienteDTO> listarClientes(String search, String status, String role, Pageable pageable) {
		if (search != null && search.isBlank()) search = null;
        if (search != null) search = search.toLowerCase();
        if (status == null || "ALL".equalsIgnoreCase(status)) status = null;
        if (role == null || role.isBlank()) role = "USER";
        role = role.toUpperCase();

        Page<ListarClienteDTO> page =
                gestionClienteRepository.findClientes(
                        search,
                        status,
                        role,
                        pageable
                );

        page.getContent().forEach(dto -> {
            if (dto.getPhoto() == null || dto.getPhoto().isEmpty()) {
                dto.setPhoto(
                    "https://ui-avatars.com/api/?name=" +
                    dto.getFullName().replace(" ", "+")
                );
            }
        });

        return page;
	}
	
	@Override
	public ListarClienteDTO obtenerCliente(String email) {
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	    return new ListarClienteDTO(
	            user.getId(),
	            user.getEmail(),
	            user.getFullName(),
	            user.getPhoto(),
	            user.getRole(),
	            user.getStatus(),
	            user.getCreatedAt()
	    );
	}
	
	@Override
	public ListarClienteDTO editarCliente(String email, EditarClienteDTO data) {
		User user = userRepository.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setFullName(data.getFullName());
        user.setRole(data.getRole());
        user.setStatus(data.getStatus());

        userRepository.save(user);

        return new ListarClienteDTO(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPhoto(),
                user.getRole(),
                user.getStatus(),
                user.getCreatedAt()
        );
	}
}
