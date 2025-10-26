package com.ecommerce.warehouse.controller;

import com.ecommerce.warehouse.model.PedidoRecebido; 
import com.ecommerce.warehouse.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class PedidoReceiverController {

    @Autowired
    private ProdutoService produtoService;

    // ESTE É O ENDPOINT CHAMADO PELO STOREFRONT
    // Endpoint: POST http://localhost:8082/processar-pedido
    @PostMapping("/processar-pedido")
    public ResponseEntity<Void> receberPedido(@RequestBody PedidoRecebido pedido) {
        
        produtoService.processarPedidoHttp(
            pedido.getNomeProduto(),
            pedido.getQuantidade()
        );
        
        return ResponseEntity.ok().build();
    }
}