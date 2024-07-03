package com.example.inventariogalpao.Galpao.controller;

import com.example.inventariogalpao.Galpao.model.Galpao;
import com.example.inventariogalpao.Galpao.service.GalpaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/galpoes")
public class GalpaoController {
    @Autowired
    private GalpaoService galpaoService;

    // Método para listar todos os galpões
    @GetMapping
    public List<Galpao> listarGalpoes() {
        return galpaoService.listarGalpoes();
    }

    // Método para encontrar um galpão pelo id
    @GetMapping("/{id}")
    public Galpao encontrarGalpao(@PathVariable String id) {
        return galpaoService.encontrarGalpao(id);
    }

    // Método para cadastrar um novo galpão
    @PostMapping
    public Galpao cadastrarGalpao(@RequestBody Galpao galpao) {
        return galpaoService.cadastrarGalpao(galpao);
    }

    // Método para deletar um galpão
    @DeleteMapping("/{id}")
    public Galpao deletarGalpao(@PathVariable String id) {
        return galpaoService.deletarGalpao(id);
    }

    // Método para atualizar um galpão
    @PutMapping("/{id}")
    public Galpao atualizarGalpao(@RequestBody Galpao galpao, @PathVariable String id) {
        return galpaoService.atualizarGalpao(id, galpao.getNome(), galpao.getEndereco());
    }

}
