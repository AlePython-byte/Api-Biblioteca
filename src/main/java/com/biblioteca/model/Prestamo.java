package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "prestamos")
public class Prestamo {

    @Id
    private String id;

    private String usuarioId;
    private String ejemplarId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private String estado;
}
