package com.example.inventariogalpao.Item;

import com.example.inventariogalpao.Galpao.Galpao;
import com.example.inventariogalpao.Galpao.GalpaoRepository;
import com.example.inventariogalpao.Setor.Setor;
import com.example.inventariogalpao.Setor.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private GalpaoRepository galpaoRepository;

    public ItemService(ItemRepository itemRepository, SetorRepository setorRepository) {
        this.itemRepository = itemRepository;
        this.setorRepository = setorRepository;
    }

    // Método para cadastrar um novo item
    public Item cadastrarItem(Item item, String setorId) {
        // Procura o setor no banco de dados
        Setor setor = setorRepository.findById(setorId).orElse(null);

        // Se o setor não existir, lança uma exceção
        if (setor == null) {
            throw new RuntimeException("Setor não encontrado");
        }

        // Verifica se o item está vazio
        if (item.getNome().isEmpty() || item.getNome() == null) {
            throw new RuntimeException("Item não pode ser vazio");
        }

        // Verifica se o item já existe no setor
        Item itemExistente = itemRepository.findByNomeAndSetorId(item.getNome(), setorId);
        if (itemExistente != null) {
            throw new RuntimeException("Item já cadastrado no setor");
        }

        // Quantidade só pode ser números positivos
        if (item.getQuantidade() < 0) {
            throw new RuntimeException("Quantidade não pode ser negativa");
        }

        // Associa o item ao setor e ao galpão
        item.setSetor(setor);
        item.setGalpao(setor.getGalpao());

        // Salva o item no banco de dados
        return itemRepository.save(item);
    }

    // Método para atualizar um item
    public Item atualizarItem(String id, String nome, String posicao, Integer quantidade) {
        // Procura o item no banco de dados
        Item itemExistente = itemRepository.findById(id).orElse(null);

        // Se o item não existir, lança uma exceção
        if (itemExistente == null) {
            throw new RuntimeException("Item não cadastrado");
        }

        // Atualiza os dados do item
        itemExistente.setNome(nome);
        itemExistente.setPosicao(posicao);
        itemExistente.setQuantidade(quantidade);

        // Salva o item atualizado no banco de dados
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

    // Método para listar todos os itens de um setor específico
    public List<Item> listarItens(String setorId) {
        // Procura o setor no banco de dados
        Setor setor = setorRepository.findById(setorId).orElse(null);

        // Se o setor não existir, lança uma exceção
        if (setor == null) {
            throw new RuntimeException("Setor não encontrado");
        }

        // Retorna todos os itens associados ao setor
        return itemRepository.findBySetorId(setorId);
    }

    // Método para listar todos os itens de um galpão
    public List<Item> listarItensGalpao(String galpaoId) {
        // Procura o galpão no banco de dados
        Galpao galpao = galpaoRepository.findById(galpaoId).orElse(null);

        // Se o galpão não existir, lança uma exceção
        if (galpao == null) {
            throw new RuntimeException("Galpão não encontrado");
        }

        // Retorna todos os itens associados ao galpão
        return itemRepository.findByGalpaoId(galpaoId);
    }

    // Método para encontrar um item pelo ID
    public Item encontrarItem(String id) {
        // Procura o item no banco de dados usando o ID
        return itemRepository.findById(id).orElse(null);
    }
}
