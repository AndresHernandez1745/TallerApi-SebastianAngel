package com.myapp.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.api.Entity.Viaje;

import java.time.LocalDate;
import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    
    // Consulta personalizada: Busca viajes por destino (ignorando mayúsculas/minúsculas)
    List<Viaje> findByDestinoContainingIgnoreCase(String destino);
    
    // Consulta personalizada: Busca viajes disponibles en un rango de fechas
    List<Viaje> findByFechasDisponiblesBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Consulta personalizada: Viajes con plazas disponibles
    List<Viaje> findByPlazasDisponiblesGreaterThan(int plazas);
}