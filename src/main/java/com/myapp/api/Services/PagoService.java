package com.myapp.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.api.DTO.PagoDTO;
import com.myapp.api.Mapper.PagoMapper;
import com.myapp.api.Repository.PagoRepository;
import com.myapp.api.Repository.ReservaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private PagoMapper pagoMapper;

    public Optional<PagoDTO> findById(Long id) {
        return pagoRepository.findById(id)
            .map(pagoMapper::toDto);
    }

    public PagoDTO save(PagoDTO pagoDTO) {
        if (!reservaRepository.existsById(pagoDTO.getReservaId())) {
            throw new RuntimeException("Reserva no encontrada");
        }

        return pagoMapper.toDto(
            pagoRepository.save(
                pagoMapper.toEntity(pagoDTO)
            )
        );
    }

    /*public List<PagoDTO> findByReservaId(Long reservaId) {
    return pagoRepository.findByReservaId(reservaId)
        .stream()
        .map(pagoMapper::toDto)
        .collect(Collectors.toList());
    }
    */

    public Optional<PagoDTO> findPagoByReservaId(Long reservaId) {
        return pagoRepository.findPagoByReservaId(reservaId)
            .map(pagoMapper::toDto);
    }

    public List<PagoDTO> findByEstado(String estado) {
        return pagoRepository.findByEstado(estado)
            .stream()
            .map(pagoMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<PagoDTO> findAll() {
        return pagoRepository.findAll()
            .stream()
            .map(pagoMapper::toDto)
            .collect(Collectors.toList());
    }


}
