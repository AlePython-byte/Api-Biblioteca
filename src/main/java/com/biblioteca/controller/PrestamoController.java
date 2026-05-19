package com.biblioteca.controller;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> crearPrestamo(@RequestBody PrestamoRequest request) {
        PrestamoResponse response = prestamoService.crearPrestamo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> listarPrestamos() {
        List<PrestamoResponse> prestamos = prestamoService.listarPrestamos();
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> consultarPrestamo(@PathVariable String id) {
        PrestamoResponse response = prestamoService.consultarPrestamo(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/devolucion")
    public ResponseEntity<PrestamoResponse> registrarDevolucion(@PathVariable String id) {
        PrestamoResponse response = prestamoService.registrarDevolucion(id);
        return ResponseEntity.ok(response);
    }
}
