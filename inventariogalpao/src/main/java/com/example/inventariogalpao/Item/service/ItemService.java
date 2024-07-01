package com.example.inventariogalpao.Item.service;

import com.example.inventariogalpao.Item.model.Item;
import com.example.inventariogalpao.Item.repository.ItemRepository;
import com.example.inventariogalpao.Setor.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SetorRepository setorRepository;

    public ItemService(ItemRepository itemRepository, SetorRepository setorRepository) {
        this.itemRepository = itemRepository;
        this.setorRepository = setorRepository;
    }

    // Método para cadastrar um novo item
    public Item cadastrarItem(Item item) {
        // Procura o item no banco de dados
        Item itemExistente = itemRepository.findByNome(item.getNome());

        // Se o item existir, lança uma exceção de item já cadastrado
        if (itemExistente != null) {
            throw new RuntimeException("Item já cadastrado");
        }

        // Verifica se o setor está presente
        if (item.getSetor().equals("") || item.getSetor() == null) {
            throw new RuntimeException("Setor não pode ser vazio");
        }

        // Verifica se o setor existe no banco de dados de setores
        if (setorRepository.findBySetor(item.getSetor()) == null) {
            throw new RuntimeException("Setor não cadastrado");
        }

        // Verifica se a posição está presente
        if (item.getPosicao().equals("") || item.getPosicao() == null) {
            throw new RuntimeException("Posição não pode ser vazia");
        }

        // Verifica se a quantidade está presente
        if (item.getQuantidade() == null) {
            throw new RuntimeException("Quantidade não pode ser vazia");
        }

        // Verifica se a quantidade é menor que 0
        if (item.getQuantidade() < 0) {
            throw new RuntimeException("Quantidade não pode ser menor que 0");
        }

        // Salva o item no banco de dados
        return itemRepository.save(item);
    }

    // Método para atualizar um item
    public Item atualizarItem(String id, String nome, String setor, String posicao, Integer quantidade) {
        // Procura o item no banco de dados
        Item itemExistente = itemRepository.findById(id).orElse(null);

        // Se o item não existir, lança uma exceção de item não encontrado
        if (itemExistente == null) {
            throw new RuntimeException("Item não cadastrado");
        }

        // Verifica se o nome do item está vazio
        if (nome.equals("") || nome == null) {
            throw new RuntimeException("Nome do item não pode ser vazio");
        }

        // Verifica se o setor existe no banco de dados de setores
        if (setorRepository.findBySetor(setor) == null) {
            throw new RuntimeException("Setor não cadastrado");
        }

        // Verifica se o setor está vazio
        if (setor.equals("") || setor == null) {
            throw new RuntimeException("Setor não pode ser vazio");
        }

        // Verifica se a posição está vazia
        if (posicao.equals("") || posicao == null) {
            throw new RuntimeException("Posição não pode ser vazia");
        }

        // Verifica se a quantidade está vazia
        if (quantidade == null) {
            throw new RuntimeException("Quantidade não pode ser vazia");
        }

        // Verifica se a quantidade é menor que 0
        if (quantidade < 0) {
            throw new RuntimeException("Quantidade não pode ser menor que 0");
        }

        // Atualiza o item no banco de dados
        itemExistente.setNome(nome);
        itemExistente.setSetor(setor);
        itemExistente.setPosicao(posicao);
        itemExistente.setQuantidade(quantidade);

        return itemRepository.save(itemExistente);
    }

    // Método para deletar um item
    public Item deletarItem(String id) {
        // Procura o item no banco de dados usando o ID
        Item itemExistente = itemRepository.findById(id).orElse(null);

        // Se o item não existir, lança uma exceção
        if (itemExistente == null) {
            throw new RuntimeException("Item não cadastrado");
        }

        // Deleta o item do banco de dados
        itemRepository.delete(itemExistente);
        return itemExistente;
    }

    // Método para listar todos os itens ou itens de um setor específico
    public List<Item> listarItens(String setor) {
        // Se o setor não for vazio, retorna todos os itens relacionados à esse setor
        if (setor != null) {
            return itemRepository.findBySetor(setor);
        }
        // Se o setor for vazio, retorna todos os itens
        return itemRepository.findAll();
    }

    // Método para encontrar um item pelo ID
    public Item encontrarItem(String id) {
        // Procura o item no banco de dados usando o ID
        return itemRepository.findById(id).orElse(null);
    }

}
