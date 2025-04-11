package com.myapp.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.api.Entity.Reserva;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    // Busca reservas por ID de cliente
    List<Reserva> findByClienteId(Long clienteId);
    
    // Busca reservas por estado (PENDIENTE, CONFIRMADA, CANCELADA)
    List<Reserva> findByEstado(String estado);
    
    // Consulta personalizada: Reservas de un viaje específico
    List<Reserva> findByViajeId(Long viajeId);
    
    // Consulta personalizada: Verifica si un cliente ya tiene reserva en un viaje
    boolean existsByClienteIdAndViajeId(Long clienteId, Long viajeId);
}