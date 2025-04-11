package com.myapp.api.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double monto;
    private LocalDate fechaPago;
    private String metodoPago; //"TARJETA", "TRANSFERENCIA", "EFECTIVO"
    private String estado; //"COMPLETADO", "PENDIENTE", "RECHAZADO"
    private String numeroTransaccion;
    
    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva; // Relación con Reserva
}