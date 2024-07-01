package com.example.inventariogalpao.Item.repository;

import com.example.inventariogalpao.Item.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String>{
    Item findByNome(String nome); // Encontra um item pelo nome
    List<Item> findBySetor(String setor); // Mostra todos os itens de um setor

}
