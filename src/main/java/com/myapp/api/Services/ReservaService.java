package com.myapp.api.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.api.DTO.ReservaDTO;
import com.myapp.api.Entity.Reserva;
import com.myapp.api.Mapper.ReservaMapper;
import com.myapp.api.Repository.ClienteRepository;
import com.myapp.api.Repository.ReservaRepository;
import com.myapp.api.Repository.ViajeRepository;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ReservaMapper reservaMapper;

    public Iterable<ReservaDTO> findAll() {
        return reservaRepository.findAll()
            .stream()
            .map(reservaMapper::toDto)
            .collect(Collectors.toList());
    }

    public Optional<ReservaDTO> findById(Long id) {
        return reservaRepository.findById(id)
            .map(reservaMapper::toDto);
    }

    public ReservaDTO save(ReservaDTO reservaDTO) {
        // Validaciones adicionales
        if (!viajeRepository.existsById(reservaDTO.getViajeId())) {
            throw new RuntimeException("Viaje no encontrado");
        }
        if (!clienteRepository.existsById(reservaDTO.getClienteId())) {
            throw new RuntimeException("Cliente no encontrado");
        }

        return reservaMapper.toDto(
            reservaRepository.save(
                reservaMapper.toEntity(reservaDTO)
            )
        );
    }

    public void delete(Long id) {
        reservaRepository.deleteById(id);
    }

    // Métodos específicos
    public List<ReservaDTO> findByClienteId(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId)
            .stream()
            .map(reservaMapper::toDto)
            .collect(Collectors.toList());
    }

    public ReservaDTO cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        
        reserva.setEstado("CANCELADA");
        return reservaMapper.toDto(reservaRepository.save(reserva));
    }
}
