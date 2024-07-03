package com.example.inventariogalpao.Setor;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "setores")
public class Setor {
    @MongoId
    private String id;
    private String setor;

    public Setor() {
    }

    public Setor(String setor) {
        this.setor = setor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
