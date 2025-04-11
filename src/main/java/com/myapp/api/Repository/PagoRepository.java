package com.myapp.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.api.Entity.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    
    // Busca pagos por estado (COMPLETADO, PENDIENTE, etc.)
    List<Pago> findByEstado(String estado);
    
    // Busca pagos asociados a una reserva
    // List<Pago> findByReservaId(Long reservaId);
    
    // Consulta personalizada: Pago asociado a una reserva específica
    Optional<Pago> findPagoByReservaId(Long reservaId);
}
