package com.biblioteca.repository;

import com.biblioteca.model.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends MongoRepository<Prestamo, String> {
}
