package com.myapp.api.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.myapp.api.DTO.ClienteDTO;
import com.myapp.api.Entity.Cliente;
import com.myapp.api.Entity.Reserva;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    
    // De Cliente (Entidad) a ClienteDTO
    @Mapping(target = "reservasIds", expression = "java(mapReservasToIds(cliente.getReservas()))")
    ClienteDTO toDto(Cliente cliente);
    
    // De ClienteDTO a Cliente (Entidad)
    @Mapping(target = "reservas", ignore = true) // Las reservas se gestionan aparte
    Cliente toEntity(ClienteDTO clienteDTO);
    
    // Método para extraer IDs de reservas
    default List<Long> mapReservasToIds(List<Reserva> reservas) {
        if (reservas == null) return null;
        return reservas.stream().map(Reserva::getId).collect(Collectors.toList());
    }
}