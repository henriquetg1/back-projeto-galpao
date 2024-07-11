package com.example.inventariogalpao.Setor;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SetorRepository extends MongoRepository<Setor, String> {
    List<Setor> findByGalpaoId(String galpaoId);
    Setor findByNomeAndGalpaoId(String nome, String galpaoId);
}
