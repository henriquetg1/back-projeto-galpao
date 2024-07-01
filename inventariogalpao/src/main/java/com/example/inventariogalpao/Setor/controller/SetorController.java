package com.example.inventariogalpao.Setor.controller;

import com.example.inventariogalpao.Setor.model.Setor;
import com.example.inventariogalpao.Setor.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setor")
public class SetorController {

    @Autowired
    private SetorService setorService;

    // Método para cadastrar um novo setor
    @PostMapping
    public Setor cadastrarSetor(@RequestBody Setor setor) {
        return setorService.cadastrarSetor(setor);
    }

    // Método para listar todos os setores
    @GetMapping
    public List<Setor> listarSetores() {
        return setorService.listarSetores();
    }

    // Método para deletar um setor
    @DeleteMapping("/{id}")
    public Setor deletarSetor(@PathVariable String id ) {
        return setorService.deletarSetor(id);
    }
}
