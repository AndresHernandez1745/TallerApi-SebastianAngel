package com.myapp.api.Controller;

import com.myapp.api.DTO.PagoDTO;
import com.myapp.api.Services.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "API para gestión de pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pago por ID")
    @ApiResponse(responseCode = "200", description = "Pago encontrado")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable Long id) {
        return pagoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@GetMapping("/reserva/{reservaId}")
    @Operation(summary = "Obtener pago por ID de reserva")
    @ApiResponse(responseCode = "200", description = "Pago encontrado")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    public ResponseEntity<PagoDTO> getPagoByReservaId(@PathVariable Long reservaId) {
        return pagoService.findByReservaId(reservaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    */

    @PostMapping
    @Operation(summary = "Registrar un nuevo pago")
    @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de pago inválidos")
    public ResponseEntity<PagoDTO> createPago(@RequestBody PagoDTO pagoDTO) {
        PagoDTO savedPago = pagoService.save(pagoDTO);
        return new ResponseEntity<>(savedPago, HttpStatus.CREATED);
    }
}