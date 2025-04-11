package com.myapp.api.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.myapp.api.DTO.ViajeDTO;
import com.myapp.api.Entity.Viaje;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViajeMapper {
    
    // Conversión de Viaje (Entidad) a ViajeDTO
    ViajeDTO toDto(Viaje viaje);
    
    // Conversión de ViajeDTO a Viaje (Entidad)
    @Mapping(target = "reservas", ignore = true) // Ignoramos las reservas al convertir de DTO a Entidad
    Viaje toEntity(ViajeDTO viajeDTO);
    
    // Lista de Viajes a Lista de ViajeDTOs (para consultas)
    List<ViajeDTO> toDtoList(List<Viaje> viajes);
}