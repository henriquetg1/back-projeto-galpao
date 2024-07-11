package com.example.inventariogalpao.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {
    @Autowired
    private ItemService itemService;

    // Método para listar todos os itens de um galpão
    @GetMapping("/galpao/{galpaoId}")
    public List<Item> listarItensGalpao(@PathVariable String galpaoId) {
        return itemService.listarItensGalpao(galpaoId);
    }

    // Método para listar todos os itens de um setor específico
    @GetMapping("/setor/{setorId}")
    public List<Item> listarItens(@PathVariable String setorId) {
        return itemService.listarItens(setorId);
    }

    // Método para encontrar um item pelo id
    @GetMapping("/{itemId}")
    public Item encontrarItem(@PathVariable String itemId) {
        return itemService.encontrarItem(itemId);
    }

    // Método para cadastrar um novo item em um setor específico
    @PostMapping("/setor/{setorId}")
    public Item cadastrarItem(@RequestBody Item item, @PathVariable String setorId) {
        return itemService.cadastrarItem(item, setorId);
    }

    // Método para deletar um item
    @DeleteMapping("/{itemId}")
    public Item deletarItem(@PathVariable String itemId) {
        return itemService.deletarItem(itemId);
    }

    // Método para atualizar um item
    @PutMapping("/editar/{itemId}")
    public Item atualizarItem(@RequestBody Item item, @PathVariable String itemId) {
        return itemService.atualizarItem(itemId, item.getNome(), item.getPosicao(), item.getQuantidade());
    }
}
