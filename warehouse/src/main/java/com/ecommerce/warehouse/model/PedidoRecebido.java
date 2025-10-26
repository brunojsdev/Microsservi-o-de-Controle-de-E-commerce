package com.ecommerce.warehouse.model;

// Esta classe define o formato do JSON que o Storefront vai enviar
public class PedidoRecebido {

    private String nomeProduto;
    private int quantidade;

    // Construtor vazio (obrigatório para JSON)
    public PedidoRecebido() {}

    // Getters e Setters (Obrigatórios para o Spring ler o JSON)
    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}