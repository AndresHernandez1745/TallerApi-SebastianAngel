package com.myapp.api.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservaDTO {
    private Long id;
    private LocalDate fechaReserva;
    private String estado;
    private Integer cantidadPersonas;
    private Long viajeId;
    private Long clienteId;
    private Long pagoId;
}
