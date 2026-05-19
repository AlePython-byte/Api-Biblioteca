package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroResponse {

    private String id;         // ← LibroResponse SÍ incluye el id
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String categoria;
}