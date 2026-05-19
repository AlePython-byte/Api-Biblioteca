package com.biblioteca.repository;

import com.biblioteca.model.Ejemplar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends MongoRepository<Ejemplar, String> {
}
