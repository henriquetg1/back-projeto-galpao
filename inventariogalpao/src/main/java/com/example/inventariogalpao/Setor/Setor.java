package com.example.inventariogalpao.Setor;

import com.example.inventariogalpao.Galpao.Galpao;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "setores")
public class Setor {
    @MongoId
    private String id;
    private String nome;

    @DBRef
    private Galpao galpao;

    public Setor() {
    }

    public Setor(String nome, Galpao galpao) {
        this.nome = nome;
        this.galpao = galpao;
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

    public Galpao getGalpao() {
        return galpao;
    }

    public void setGalpao(Galpao galpao) {
        this.galpao = galpao;
    }

}
