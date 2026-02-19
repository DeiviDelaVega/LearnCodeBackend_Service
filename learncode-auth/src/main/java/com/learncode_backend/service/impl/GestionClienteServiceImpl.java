package com.learncode_backend.service.impl;

import java.util.UUID;
import com.learncode_backend.model.User;
import com.learncode_backend.repository.GestionClienteRepository;
import com.learncode_backend.repository.UserRepository;
import com.learncode_backend.service.GestionClienteService;
import com.learncode_backend.utils.ModeloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GestionClienteServiceImpl extends ICRUDImpl<User, UUID> implements GestionClienteService {
	@Autowired
    private GestionClienteRepository repo;

    @Autowired
    private UserRepository userRepository;
	
    @Override
    public JpaRepository<User, UUID> getRepository() {
        return repo;
    }
    
	@Override
	public Page<User> listarClientes(String search, String status, String role, Pageable pageable) {
		if (search != null && search.isBlank())
            search = null;

        if (status == null || status.equalsIgnoreCase("ALL"))
            status = null;

        if (role == null || role.isBlank())
            role = null;
        
        return repo.findClientes(search, status, role, pageable);
	}

	@Override
	public User obtenerCliente(String email) {
		return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ModeloNotFoundException(
                                "Cliente no existe con email: " + email));
	}

	@Override
	public User editarCliente(String email, User user) {
		User usuarioBD = userRepository.findByEmail(email)
	            .orElseThrow(() ->
	                    new ModeloNotFoundException(
	                            "Cliente no existe con email: " + email));

	    usuarioBD.setFullName(user.getFullName());
	    usuarioBD.setRole(user.getRole());
	    usuarioBD.setStatus(user.getStatus());

	    return repo.save(usuarioBD);
	}
}
