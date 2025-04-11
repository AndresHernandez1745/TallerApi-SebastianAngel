package com.myapp.api.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PagoDTO {
    private Long id;
    private Double monto;
    private LocalDate fechaPago;
    private String metodoPago;
    private String estado;
    private String numeroTransaccion;
    private Long reservaId;
}