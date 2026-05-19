package com.biblioteca.service;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;

import java.util.List;

public interface PrestamoService {

    PrestamoResponse crearPrestamo(PrestamoRequest request);

    PrestamoResponse registrarDevolucion(String id);

    PrestamoResponse consultarPrestamo(String id);

    List<PrestamoResponse> listarPrestamos();
}
