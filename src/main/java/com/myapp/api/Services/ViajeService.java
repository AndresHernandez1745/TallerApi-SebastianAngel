package com.myapp.api.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.api.DTO.ViajeDTO;
import com.myapp.api.Mapper.ViajeMapper;
import com.myapp.api.Repository.ViajeRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private ViajeMapper viajeMapper;

    public Iterable<ViajeDTO> findAll() {
        return viajeRepository.findAll()
            .stream()
            .map(viajeMapper::toDto)
            .collect(Collectors.toList());
    }

    public Optional<ViajeDTO> findById(Long id) {
        return viajeRepository.findById(id)
            .map(viajeMapper::toDto);
    }

    public ViajeDTO save(ViajeDTO viajeDTO) {
        return viajeMapper.toDto(
            viajeRepository.save(
                viajeMapper.toEntity(viajeDTO)
            )
        );
    }

    public ViajeDTO update(ViajeDTO viajeDTO) {
        if (!viajeRepository.existsById(viajeDTO.getId())) {
            throw new RuntimeException("Viaje no encontrado");
        }
        return viajeMapper.toDto(
            viajeRepository.save(
                viajeMapper.toEntity(viajeDTO)
            )
        );
    }

    public void delete(Long id) {
        viajeRepository.deleteById(id);
    }

    // Métodos específicos para viajes
    public List<ViajeDTO> findByDestino(String destino) {
        return viajeRepository.findByDestinoContainingIgnoreCase(destino)
            .stream()
            .map(viajeMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<ViajeDTO> findAvailableBetweenDates(LocalDate start, LocalDate end) {
        return viajeRepository.findByFechasDisponiblesBetween(start, end)
            .stream()
            .map(viajeMapper::toDto)
            .collect(Collectors.toList());
    }
}