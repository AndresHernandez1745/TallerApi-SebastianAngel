package com.myapp.api.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String destino;
    private Integer duracionDias;
    private Double precio;
    private String descripcion;
    
    @ElementCollection
    private List<LocalDate> fechasDisponibles; // Fechas en las que el viaje está disponible
    
    private Integer plazasDisponibles;
    
    @OneToMany(mappedBy = "viaje")
    private List<Reserva> reservas; // Relación con Reserva
}
