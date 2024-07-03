package com.example.inventariogalpao.Galpao.repository;

import com.example.inventariogalpao.Galpao.model.Galpao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GalpaoRepository extends MongoRepository<Galpao, String> {
    Galpao findByNome(String nome); // Encontra um galpão pelo nome

}
