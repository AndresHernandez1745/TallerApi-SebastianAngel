package com.myapp.api.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.myapp.api.DTO.PagoDTO;
import com.myapp.api.Entity.Pago;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    
    // De Pago (Entidad) a PagoDTO
    @Mapping(source = "reserva.id", target = "reservaId")
    PagoDTO toDto(Pago pago);
    
    // De PagoDTO a Pago (Entidad)
    @Mapping(target = "reserva", ignore = true) // Se asigna después
    Pago toEntity(PagoDTO pagoDTO);
}
