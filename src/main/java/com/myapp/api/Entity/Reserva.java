package com.myapp.api.Entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate fechaReserva;
    private String estado; // "PENDIENTE", "CONFIRMADA", "CANCELADA"
    private Integer cantidadPersonas;
    
    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private Viaje viaje; // Relación con Viaje
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // Relación con Cliente
    
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    private Pago pago; // Relación con Pago
}
