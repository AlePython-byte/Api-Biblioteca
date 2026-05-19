package com.biblioteca.controller;

import com.biblioteca.dto.LibroRequest;
import com.biblioteca.dto.LibroResponse;
import com.biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // ─────────────────────────────────────────────
    // POST /api/libros
    // Crear un nuevo libro
    // ─────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<LibroResponse> crearLibro(@RequestBody LibroRequest request) {
        LibroResponse response = libroService.crearLibro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ─────────────────────────────────────────────
    // GET /api/libros
    // Listar todos los libros
    // ─────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<LibroResponse>> listarLibros() {
        List<LibroResponse> libros = libroService.listarLibros();
        return ResponseEntity.ok(libros);
    }

    // ─────────────────────────────────────────────
    // GET /api/libros/{id}
    // Consultar un libro por su ID
    // ─────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<LibroResponse> consultarLibro(@PathVariable String id) {
        LibroResponse response = libroService.consultarLibro(id);
        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────────────
    // PUT /api/libros/{id}
    // Actualizar un libro existente
    // ─────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> actualizarLibro(
            @PathVariable String id,
            @RequestBody LibroRequest request) {
        LibroResponse response = libroService.actualizarLibro(id, request);
        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────────────
    // DELETE /api/libros/{id}
    // Eliminar un libro
    // ─────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}