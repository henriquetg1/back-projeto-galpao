package com.example.inventariogalpao.Item;

import com.example.inventariogalpao.Galpao.Galpao;
import com.example.inventariogalpao.Setor.Setor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "itens")
public class Item {
    @MongoId
    private String id;
    private String nome;
    private String posicao;
    private Integer quantidade;

    @DBRef
    private Galpao galpao;

    @DBRef
    private Setor setor;

    public Item() {
    }

    public Item(String nome, String posicao, Integer quantidade, Galpao galpao, Setor setor) {
        this.nome = nome;
        this.posicao = posicao;
        this.quantidade = quantidade;
        this.galpao = galpao;
        this.setor = setor;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Galpao getGalpao() {
        return galpao;
    }

    public void setGalpao(Galpao galpao) {
        this.galpao = galpao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
