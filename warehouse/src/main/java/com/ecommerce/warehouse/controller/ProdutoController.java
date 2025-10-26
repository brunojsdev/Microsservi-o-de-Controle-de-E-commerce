package com.ecommerce.warehouse.controller;

import com.ecommerce.warehouse.model.Produto;
import com.ecommerce.warehouse.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // Endpoint: POST /produtos (Para iniciar/atualizar o estoque)
    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = service.salvarProduto(produto);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    // Endpoint: GET /produtos (Para consultar o estoque)
    @GetMapping
    public ResponseEntity<java.util.List<Produto>> listarProdutos() {
        return ResponseEntity.ok(service.listarProdutos());
    }
}