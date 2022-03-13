package com.example.containerapp.repository;

import com.example.containerapp.model.Container;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContainerRepository extends MongoRepository<Container, String> {

     Optional<Container> findById(String id);
}
