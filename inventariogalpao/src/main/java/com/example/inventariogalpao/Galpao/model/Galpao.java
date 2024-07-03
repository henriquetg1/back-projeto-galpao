package com.example.inventariogalpao.Galpao.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "galpoes")
public class Galpao {
    @MongoId
    private String id;
    private String nome;
    private String endereco;

    public Galpao() {
    }

    public Galpao(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


}
