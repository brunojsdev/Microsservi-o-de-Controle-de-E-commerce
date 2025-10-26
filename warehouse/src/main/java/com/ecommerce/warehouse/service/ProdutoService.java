package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.model.Produto;
import com.ecommerce.warehouse.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    // Métodos para gerenciar o estoque via HTTP (POST /produtos e GET /produtos)
    public Produto salvarProduto(Produto produto) {
        return repository.save(produto);
    }

    public java.util.List<Produto> listarProdutos() {
        return repository.findAll();
    }

    // LÓGICA DE PROCESSAMENTO DO PEDIDO (Recebe por HTTP)
    public void processarPedidoHttp(String nomeProduto, int quantidade) {
        System.out.println("------------------------------------");
        System.out.println("NOVO PEDIDO RECEBIDO (Warehouse - HTTP): ");
        System.out.println("Produto: " + nomeProduto);
        System.out.println("Quantidade: " + quantidade);

        // Baixa o estoque
        processarBaixaEstoque(nomeProduto, quantidade);

        System.out.println("------------------------------------");
    }


    // MÉTODO PRIVADO (Define a lógica de baixa)
    private void processarBaixaEstoque(String nomeProduto, int quantidade) {
        Optional<Produto> produtoOpt = repository.findById(nomeProduto);

        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();

            if (produto.getQuantidadeEstoque() >= quantidade) {
                // Diminui o estoque
                int novoEstoque = produto.getQuantidadeEstoque() - quantidade;
                produto.setQuantidadeEstoque(novoEstoque);
                repository.save(produto);

                System.out.println("Estoque atualizado: Novo estoque de " + nomeProduto + ": " + novoEstoque);

            } else {
                System.err.println("ERRO: Estoque insuficiente! Não é possível enviar " + nomeProduto + ".");
            }
        } else {
            System.err.println("ERRO: Produto " + nomeProduto + " não encontrado no Armazém.");
        }
    }
}