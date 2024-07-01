package com.example.inventariogalpao.Item.controller;

import com.example.inventariogalpao.Item.model.Item;
import com.example.inventariogalpao.Item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/itens")
public class ItemController {
    @Autowired
    private ItemService itemService;

    // Método para listar todos os itens
    @GetMapping
    public List<Item> listarItens(@RequestParam(required = false) String setor) {
        return itemService.listarItens(setor);
    }

    @GetMapping("/{id}")
    // Método para encontrar um item pelo id
    public Item encontrarItem(@PathVariable String id) {
        return itemService.encontrarItem(id);
    }

    @PostMapping
    // Método para cadastrar um novo item
    public Item cadastrarItem(@RequestBody Item beneficio) {
        return itemService.cadastrarItem(beneficio);
    }

    @DeleteMapping("/{id}")
    // Método para deletar um item
    public Item deletarItem(@PathVariable String id) {
        return itemService.deletarItem(id);
    }

    @PutMapping("/{id}")
    // Método para atualizar um item
    public Item atualizarItem(@RequestBody Item item, @PathVariable String id) {
        return itemService.atualizarItem(id, item.getSetor(), item.getNome(), item.getPosicao(), item.getQuantidade());
    }

}