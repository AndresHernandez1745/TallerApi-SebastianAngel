package com.myapp.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.api.Entity.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Busca cliente por email (útil para login/registro)
    Optional<Cliente> findByEmail(String email);
    
    // Verifica si un email ya está registrado
    boolean existsByEmail(String email);
}
