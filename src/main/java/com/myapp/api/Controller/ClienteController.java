package com.myapp.api.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myapp.api.DTO.ClienteDTO;
import com.myapp.api.Services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gestión de clientes")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes encontrada")
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok((List<ClienteDTO>) clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de cliente inválidos")
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO savedCliente = clienteService.save(clienteDTO);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        return ResponseEntity.ok(clienteService.update(clienteDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}