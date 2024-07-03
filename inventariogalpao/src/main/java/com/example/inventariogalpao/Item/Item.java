package com.example.inventariogalpao.Item;

import com.example.inventariogalpao.Galpao.Galpao;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "galpoes")
public class Item {
    @MongoId
    private String id;
    private String nome;
    private String setor; // Refere-se ao setor do galpão (ferramentas, eletrônicos, etc)
    private String posicao; // Refere-se a posição do item no galpão (prateleira A1, A2, B1, etc)
    private Integer quantidade;
    // Um item faz parte de um galpão, então poderíamos ter um atributo do tipo Galpao aqui
    private Galpao galpao;

    public Item() {
    }

    public Item(String nome, String setor, String posicao, Integer quantidade, Galpao galpao) {
        this.nome = nome;
        this.setor = setor;
        this.posicao = posicao;
        this.quantidade = quantidade;
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

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
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
}
