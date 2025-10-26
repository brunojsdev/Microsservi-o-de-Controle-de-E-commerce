package com.ecommerce.storefront.controller;

import com.ecommerce.storefront.model.Pedido;
import com.ecommerce.storefront.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido novoPedido) {
        Pedido pedidoCriado = service.criarNovoPedido(
            novoPedido.getProdutoNome(),
            novoPedido.getQuantidade()
        );
        return new ResponseEntity<>(pedidoCriado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<java.util.List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(service.buscarTodos());
    }
}