package com.myapp.api.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.myapp.api.DTO.ReservaDTO;
import com.myapp.api.Entity.Reserva;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ViajeMapper.class, ClienteMapper.class})
public interface ReservaMapper {
    
    // De Reserva (Entidad) a ReservaDTO
    @Mapping(source = "viaje.id", target = "viajeId")
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "pago.id", target = "pagoId")
    ReservaDTO toDto(Reserva reserva);
    
    // De ReservaDTO a Reserva (Entidad)
    @Mapping(target = "viaje", ignore = true) // Se asigna después
    @Mapping(target = "cliente", ignore = true) // Se asigna después
    @Mapping(target = "pago", ignore = true) // Se asigna después
    Reserva toEntity(ReservaDTO reservaDTO);
    
    // Lista de Reservas a Lista de ReservaDTOs
    List<ReservaDTO> toDtoList(List<Reserva> reservas);
}
