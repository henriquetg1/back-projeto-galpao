package com.example.inventariogalpao.Item;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findBySetorId(String setorId);
    Item findByNomeAndSetorId(String nome, String setorId);
    List<Item> findByGalpaoId(String galpaoId);

    // DeleteBySetorId
    void deleteBySetorId(String setorId);
}
