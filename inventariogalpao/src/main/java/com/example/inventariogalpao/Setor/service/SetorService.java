package com.example.inventariogalpao.Setor.service;

import com.example.inventariogalpao.Item.model.Item;
import com.example.inventariogalpao.Item.repository.ItemRepository;
import com.example.inventariogalpao.Setor.model.Setor;
import com.example.inventariogalpao.Setor.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private ItemRepository itemRepository;

    public SetorService(SetorRepository setorRepository, ItemRepository itemRepository) {
        this.setorRepository = setorRepository;
        this.itemRepository = itemRepository;
    }

    // Método para cadastrar um novo setor
    public Setor cadastrarSetor(Setor setor) {
        // Procura o setor no banco de dados
        Setor setorExistente = setorRepository.findBySetor(setor.getSetor());

        // Se o setor existir, lança uma exceção
        if (setorExistente != null) {
            throw new RuntimeException("Setor já cadastrado");
        }

        // Verifica se o setor está vazio
        if (setor.getSetor().isEmpty() || setor.getSetor() == null) {
            throw new RuntimeException("Setor não pode ser vazio");
        }

        // Salva o setor no banco de dados
        return setorRepository.save(setor);
    }

    // Método para listar todos os setores
    public List<Setor> listarSetores() {
        return setorRepository.findAll();
    }

    // Método para deletar um setor
    public Setor deletarSetor(String id) {
        // Procura o setor no banco de dados
        Setor setorExistente = setorRepository.findById(id).orElse(null);

        // Se o setor não existir, lança uma exceção
        if (setorExistente == null) {
            throw new RuntimeException("Setor não encontrado");
        }

        // Verifica se existem itens associados ao setor
        List<Item> itens = itemRepository.findBySetor(setorExistente.getSetor());

        // Se existirem itens associados ao setor, lança uma exceção
        if (!itens.isEmpty()) {
            throw new RuntimeException("Existem itens associados a este setor");
        }

        // Deleta o setor do banco de dados
        setorRepository.delete(setorExistente);
        return setorExistente;
    }



}
