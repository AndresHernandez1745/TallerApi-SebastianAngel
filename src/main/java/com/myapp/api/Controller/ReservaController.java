package com.myapp.api.Controller;


import com.myapp.api.DTO.ReservaDTO;
import com.myapp.api.Services.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "API para gestión de reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las reservas")
    @ApiResponse(responseCode = "200", description = "Lista de reservas encontrada")
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        return ResponseEntity.ok((List<ReservaDTO>) reservaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reserva por ID")
    @ApiResponse(responseCode = "200", description = "Reserva encontrada")
    @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Obtener reservas por cliente")
    @ApiResponse(responseCode = "200", description = "Reservas encontradas")
    public ResponseEntity<List<ReservaDTO>> getReservasByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(reservaService.findByClienteId(clienteId));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva reserva")
    @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de reserva inválidos")
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO savedReserva = reservaService.save(reservaDTO);
        return new ResponseEntity<>(savedReserva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar una reserva")
    @ApiResponse(responseCode = "200", description = "Reserva cancelada")
    @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    public ResponseEntity<ReservaDTO> cancelReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelarReserva(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una reserva")
    @ApiResponse(responseCode = "204", description = "Reserva eliminada")
    @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}