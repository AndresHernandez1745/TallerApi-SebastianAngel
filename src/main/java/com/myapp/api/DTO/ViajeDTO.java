package com.myapp.api.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ViajeDTO {
    private Long id;
    private String destino;
    private Integer duracionDias;
    private Double precio;
    private String descripcion;
    private List<LocalDate> fechasDisponibles;
    private Integer plazasDisponibles;
}

