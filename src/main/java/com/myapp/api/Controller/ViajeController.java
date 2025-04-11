package com.myapp.api.Controller;

import com.myapp.api.DTO.ViajeDTO;
import com.myapp.api.Services.ViajeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/viajes")
@Tag(name = "Viajes", description = "API para gestión de viajes")
public class ViajeController {

    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los viajes")
    @ApiResponse(responseCode = "200", description = "Lista de viajes encontrada")
    public ResponseEntity<List<ViajeDTO>> getAllViajes() {
        return ResponseEntity.ok((List<ViajeDTO>) viajeService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener viaje por ID")
    @ApiResponse(responseCode = "200", description = "Viaje encontrado")
    @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    public ResponseEntity<ViajeDTO> getViajeById(@PathVariable Long id) {
        return viajeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Buscar viajes disponibles entre fechas")
    @ApiResponse(responseCode = "200", description = "Viajes encontrados")
    public ResponseEntity<List<ViajeDTO>> getViajesDisponibles(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(viajeService.findAvailableBetweenDates(fechaInicio, fechaFin));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo viaje")
    @ApiResponse(responseCode = "201", description = "Viaje creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de viaje inválidos")
    public ResponseEntity<ViajeDTO> createViaje(@RequestBody ViajeDTO viajeDTO) {
        ViajeDTO savedViaje = viajeService.save(viajeDTO);
        return new ResponseEntity<>(savedViaje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un viaje existente")
    @ApiResponse(responseCode = "200", description = "Viaje actualizado")
    @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    public ResponseEntity<ViajeDTO> updateViaje(@PathVariable Long id, @RequestBody ViajeDTO viajeDTO) {
        viajeDTO.setId(id);
        return ResponseEntity.ok(viajeService.update(viajeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un viaje")
    @ApiResponse(responseCode = "204", description = "Viaje eliminado")
    @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    public ResponseEntity<Void> deleteViaje(@PathVariable Long id) {
        viajeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
