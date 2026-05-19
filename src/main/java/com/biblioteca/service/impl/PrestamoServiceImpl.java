package com.biblioteca.service.impl;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.model.Ejemplar;
import com.biblioteca.model.Prestamo;
import com.biblioteca.repository.EjemplarRepository;
import com.biblioteca.repository.PrestamoRepository;
import com.biblioteca.repository.UsuarioRepository;
import com.biblioteca.service.PrestamoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EjemplarRepository ejemplarRepository;

    public PrestamoServiceImpl(
            PrestamoRepository prestamoRepository,
            UsuarioRepository usuarioRepository,
            EjemplarRepository ejemplarRepository) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public PrestamoResponse crearPrestamo(PrestamoRequest request) {
        if (!usuarioRepository.existsById(request.getUsuarioId())) {
            throw new RuntimeException("Usuario no encontrado con id: " + request.getUsuarioId());
        }

        Ejemplar ejemplar = ejemplarRepository.findById(request.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + request.getEjemplarId()));

        if (!"DISPONIBLE".equalsIgnoreCase(ejemplar.getEstado())) {
            throw new RuntimeException("El ejemplar no esta disponible");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuarioId(request.getUsuarioId());
        prestamo.setEjemplarId(request.getEjemplarId());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setFechaDevolucionEsperada(request.getFechaDevolucionEsperada());
        prestamo.setEstado("ACTIVO");

        ejemplar.setEstado("PRESTADO");
        ejemplarRepository.save(ejemplar);

        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        return mapToResponse(prestamoGuardado);
    }

    @Override
    public PrestamoResponse registrarDevolucion(String id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + id));

        Ejemplar ejemplar = ejemplarRepository.findById(prestamo.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + prestamo.getEjemplarId()));

        prestamo.setEstado("DEVUELTO");
        ejemplar.setEstado("DISPONIBLE");

        ejemplarRepository.save(ejemplar);
        Prestamo prestamoActualizado = prestamoRepository.save(prestamo);

        return mapToResponse(prestamoActualizado);
    }

    @Override
    public PrestamoResponse consultarPrestamo(String id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + id));

        return mapToResponse(prestamo);
    }

    @Override
    public List<PrestamoResponse> listarPrestamos() {
        return prestamoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PrestamoResponse mapToResponse(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getUsuarioId(),
                prestamo.getEjemplarId(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucionEsperada(),
                prestamo.getEstado()
        );
    }
}
