package com.example.inventariogalpao.Setor;

import com.example.inventariogalpao.Galpao.Galpao;
import com.example.inventariogalpao.Galpao.GalpaoRepository;
import com.example.inventariogalpao.Item.Item;
import com.example.inventariogalpao.Item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private GalpaoRepository galpaoRepository;

    public SetorService(SetorRepository setorRepository, ItemRepository itemRepository, GalpaoRepository galpaoRepository) {
        this.setorRepository = setorRepository;
        this.itemRepository = itemRepository;
        this.galpaoRepository = galpaoRepository;
    }

    // Método para cadastrar um novo setor
    public Setor cadastrarSetor(Setor setor, String galpaoId) {
        // Procura o galpão no banco de dados
        Galpao galpao = galpaoRepository.findById(galpaoId).orElse(null);

        // Se o galpão não existir, lança uma exceção
        if (galpao == null) {
            throw new RuntimeException("Galpão não encontrado");
        }

        // Verifica se o setor está vazio
        if (setor.getNome().isEmpty() || setor.getNome() == null) {
            throw new RuntimeException("Setor não pode ser vazio");
        }

        // Verifica se o setor já existe no galpão
        Setor setorExistente = setorRepository.findByNomeAndGalpaoId(setor.getNome(), galpaoId);
        if (setorExistente != null) {
            throw new RuntimeException("Setor já cadastrado no galpão");
        }

        // Associa o setor ao galpão
        setor.setGalpao(galpao);

        // Salva o setor no banco de dados
        return setorRepository.save(setor);
    }

    // Método para listar todos os setores de um galpão
    public List<Setor> listarSetores(String galpaoId) {
        // Verifica se o galpão existe
        Galpao galpao = galpaoRepository.findById(galpaoId).orElse(null);
        if (galpao == null) {
            throw new RuntimeException("Galpão não encontrado");
        }

        // Retorna todos os setores associados ao galpão
        return setorRepository.findByGalpaoId(galpaoId);
    }

    // Método para deletar um setor
    public Setor deletarSetor(String id) {
        Setor setorExistente = setorRepository.findById(id).orElse(null);
        if (setorExistente == null) {
            throw new RuntimeException("Setor não encontrado");
        }

        // Encontrar e deletar itens associados ao setor
        List<Item> itens = itemRepository.findBySetorId(id);
        if (!itens.isEmpty()) {
            itemRepository.deleteAll(itens);
        }

        // Deleta o setor do banco de dados
        setorRepository.delete(setorExistente);
        return setorExistente;
    }

    // Método para atualizar um setor
    public Setor atualizarSetor(String id, String nome) {
        // Procura o setor no banco de dados
        Setor setorExistente = setorRepository.findById(id).orElse(null);

        // Se o setor não existir, lança uma exceção
        if (setorExistente == null) {
            throw new RuntimeException("Setor não encontrado");
        }

        // Verifica se o nome do setor está vazio
        if (nome.isEmpty() || nome == null) {
            throw new RuntimeException("Nome do setor não pode ser vazio");
        }

        // Atualiza o nome do setor
        setorExistente.setNome(nome);

        // Salva o setor no banco de dados
        return setorRepository.save(setorExistente);
    }

    // Método para listar todos os itens de um setor
    public List<Item> listarItensSetor(String setorId) {
        // Procura o setor no banco de dados
        Setor setorExistente = setorRepository.findById(setorId).orElse(null);

        // Se o setor não existir, lança uma exceção
        if (setorExistente == null) {
            throw new RuntimeException("Setor não encontrado");
        }

        // Retorna todos os itens associados ao setor
        return itemRepository.findBySetorId(setorId);
    }

    // Método para encontrar um setor pelo id
    public Setor encontrarSetor(String id) {
        // Procura o setor no banco de dados
        Setor setorExistente = setorRepository.findById(id).orElse(null);

        // Se o setor não existir, lança uma exceção
        if (setorExistente == null) {
            throw new RuntimeException("Setor não encontrado");
        }

        return setorExistente;
    }
}
