package com.biblioteca.service.impl;

import com.biblioteca.dto.LibroRequest;
import com.biblioteca.dto.LibroResponse;
import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    // Spring Boot inyecta automáticamente el repositorio aquí (constructor injection)
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public LibroResponse crearLibro(LibroRequest request) {
        // 1. Crear un nuevo objeto Libro con los datos del request
        Libro libro = new Libro();
        libro.setIsbn(request.getIsbn());
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setCategoria(request.getCategoria());

        // 2. Guardar en MongoDB (save() retorna el objeto con el ID generado)
        Libro libroGuardado = libroRepository.save(libro);

        // 3. Convertir el Libro guardado a LibroResponse y retornarlo
        return mapToResponse(libroGuardado);
    }

    @Override
    public LibroResponse actualizarLibro(String id, LibroRequest request) {
        // 1. Buscar el libro existente. Si no existe, lanzar excepción
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));

        // 2. Actualizar los campos con los nuevos datos
        libro.setIsbn(request.getIsbn());
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setCategoria(request.getCategoria());

        // 3. Guardar los cambios en MongoDB
        Libro libroActualizado = libroRepository.save(libro);

        // 4. Retornar la respuesta
        return mapToResponse(libroActualizado);
    }

    @Override
    public void eliminarLibro(String id) {
        // 1. Verificar que el libro exista antes de eliminar
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }

        // 2. Eliminar el documento de MongoDB
        libroRepository.deleteById(id);
    }

    @Override
    public LibroResponse consultarLibro(String id) {
        // Buscar el libro. Si no existe, lanzar excepción
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));

        return mapToResponse(libro);
    }

    @Override
    public List<LibroResponse> listarLibros() {
        // Obtener todos los libros y convertirlos a LibroResponse usando Java Streams
        return libroRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ─────────────────────────────────────────────
    // Método auxiliar: convierte Libro → LibroResponse
    // ─────────────────────────────────────────────
    private LibroResponse mapToResponse(Libro libro) {
        return new LibroResponse(
                libro.getId(),
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getAnioPublicacion(),
                libro.getCategoria()
        );
    }
}