package com.example.inventariogalpao.Setor;

import com.example.inventariogalpao.Item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    // Método para encontrar um setor pelo id
    @GetMapping("/{id}")
    public Setor encontrarSetor(@PathVariable String id) {
        return setorService.encontrarSetor(id);
    }

    // Método para cadastrar um novo setor
    @PostMapping("/galpao/{galpaoId}")
    public Setor cadastrarSetor(@RequestBody Setor setor, @PathVariable String galpaoId) {
        return setorService.cadastrarSetor(setor, galpaoId);
    }

    // Método para listar todos os setores de um galpão específico
    @GetMapping("/galpao/{galpaoId}")
    public List<Setor> listarSetores(@PathVariable String galpaoId) {
        return setorService.listarSetores(galpaoId);
    }

    // Método para deletar um setor
    @DeleteMapping("/{id}")
    public Setor deletarSetor(@PathVariable String id) {
        return setorService.deletarSetor(id);
    }

    // Método para atualizar um setor
    @PutMapping("/{id}")
    public Setor atualizarSetor(@RequestBody Setor setor, @PathVariable String id) {
        return setorService.atualizarSetor(id, setor.getNome());
    }

    // Método para listar todos os itens de um setor específico
    @GetMapping("/{setorId}/itens")
    public List<Item> listarItensSetor(@PathVariable String setorId) {
        return setorService.listarItensSetor(setorId);
    }
}
