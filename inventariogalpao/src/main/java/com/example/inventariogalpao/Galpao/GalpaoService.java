package com.example.inventariogalpao.Galpao;

import com.example.inventariogalpao.Item.ItemRepository;
import com.example.inventariogalpao.Setor.Setor;
import com.example.inventariogalpao.Setor.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalpaoService {
    @Autowired
    private GalpaoRepository galpaoRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private ItemRepository itemRepository;

    public GalpaoService(GalpaoRepository galpaoRepository) {
        this.galpaoRepository = galpaoRepository;
    }

    // Método para cadastrar um novo galpão
    public Galpao cadastrarGalpao(Galpao galpao) {
        // Procura o galpão no banco de dados
        Galpao galpaoExistente = galpaoRepository.findByNome(galpao.getNome());

        // Se o item existir, lança uma exceção de galpão já cadastrado
        if (galpaoExistente != null) {
            throw new RuntimeException("Galpão já cadastrado");
        }

        // Verifica se o nome do galpão está vazio
        if (galpao.getNome().equals("") || galpao.getNome() == null) {
            throw new RuntimeException("Nome do galpão não pode ser vazio");
        }

        // Verifica se possui endereço
        if (galpao.getEndereco().equals("") || galpao.getEndereco() == null) {
            throw new RuntimeException("Endereço não pode ser vazio");
        }

        // Salva o galpão no banco de dados
        return galpaoRepository.save(galpao);
    }

    // Método para atualizar um galpão
    public Galpao atualizarGalpao(String id, String nome, String endereco) {
        // Procura o galpão no banco de dados
        Galpao galpaoExistente = galpaoRepository.findById(id).orElse(null);

        // Se o galpão não existir, lança uma exceção de galpão não encontrado
        if (galpaoExistente == null) {
            throw new RuntimeException("Galpão não cadastrado");
        }

        // Verifica se o nome do galpão está vazio
        if (nome.equals("") || nome == null) {
            throw new RuntimeException("Nome do galpão não pode ser vazio");
        }

        // Verifica se possui endereço
        if (endereco.equals("") || endereco == null) {
            throw new RuntimeException("Endereço não pode ser vazio");
        }

        // Atualiza o nome e o endereço do galpão
        galpaoExistente.setNome(nome);
        galpaoExistente.setEndereco(endereco);

        // Salva o galpão no banco de dados
        return galpaoRepository.save(galpaoExistente);
    }

    // Método para deletar um galpão
    public Galpao deletarGalpao(String id) {
        Galpao galpaoExistente = galpaoRepository.findById(id).orElse(null);
        if (galpaoExistente == null) {
            throw new RuntimeException("Galpão não cadastrado");
        }

        // Encontrar e deletar setores relacionados
        List<Setor> setores = setorRepository.findByGalpaoId(id);
        for (Setor setor : setores) {
            // Encontrar e deletar itens relacionados ao setor
            itemRepository.deleteBySetorId(setor.getId());
        }
        setorRepository.deleteAll(setores);

        // Deleta o galpão
        galpaoRepository.delete(galpaoExistente);

        return galpaoExistente;
    }

    // Método para listar todos os galpões
    public List<Galpao> listarGalpoes() {
        // Retorna todos os galpões
        return galpaoRepository.findAll();
    }

    // Método para encontrar um galpão pelo id
    public Galpao encontrarGalpao(String id) {
        // Procura o galpão no banco de dados
        Galpao galpaoExistente = galpaoRepository.findById(id).orElse(null);

        // Se o galpão não existir, lança uma exceção de galpão não encontrado
        if (galpaoExistente == null) {
            throw new RuntimeException("Galpão não cadastrado");
        }

        return galpaoExistente;
    }
}

