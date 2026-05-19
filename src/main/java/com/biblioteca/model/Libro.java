package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "libros")
public class Libro {

    @Id
    private String id;

    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String categoria;
}