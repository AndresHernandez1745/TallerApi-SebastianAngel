package com.myapp.api.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private List<Long> reservasIds;
    // Solo los IDs de las reservas para evitar carga de datos
}
