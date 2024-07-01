package com.example.inventariogalpao.Setor.repository;

import com.example.inventariogalpao.Setor.model.Setor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SetorRepository extends MongoRepository<Setor, String> {
    Setor findBySetor(String setor);

}
