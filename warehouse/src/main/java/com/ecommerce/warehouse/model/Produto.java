package com.ecommerce.warehouse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Produto {

    // Usaremos o nome como chave primária para simplificar o estoque
    @Id
    private String nome;
    private int quantidadeEstoque;

    public Produto() {
    }

    public Produto(String nome, int quantidadeEstoque) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // --- Getters e Setters ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}