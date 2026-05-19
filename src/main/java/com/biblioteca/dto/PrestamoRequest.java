package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoRequest {

    private String usuarioId;
    private String ejemplarId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
}
