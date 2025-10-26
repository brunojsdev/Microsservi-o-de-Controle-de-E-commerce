package com.ecommerce.storefront.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String produtoNome;
    private int quantidade;
    private String status;

    public Pedido() {}

    public Pedido(String produtoNome, int quantidade) {
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.status = "PENDENTE"; // Status inicial
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProdutoNome() { return produtoNome; }
    public void setProdutoNome(String produtoNome) { this.produtoNome = produtoNome; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}