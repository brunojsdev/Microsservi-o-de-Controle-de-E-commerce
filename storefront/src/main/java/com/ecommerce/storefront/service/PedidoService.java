package com.ecommerce.storefront.service;

import com.ecommerce.storefront.model.Pedido;
import com.ecommerce.storefront.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient; // ESSENCIAL: FERRAMENTA HTTP

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    // WebClient para fazer a comunicação HTTP com o Warehouse (porta 8082)
    private final WebClient webClient = WebClient.create("http://localhost:8082");

    public Pedido criarNovoPedido(String produtoNome, int quantidade) {
        // 1. Cria e salva o pedido localmente
        Pedido novoPedido = new Pedido(produtoNome, quantidade);
        Pedido pedidoSalvo = repository.save(novoPedido);

        // Objeto JSON simples para enviar ao Warehouse
        String jsonBody = String.format("{\"nomeProduto\": \"%s\", \"quantidade\": %d}", produtoNome, quantidade);

        try {
            // 2. Envia a requisição HTTP POST para o Warehouse
            webClient.post()
                     .uri("/processar-pedido") // Endpoint no Warehouse
                     .header("Content-Type", "application/json")
                     .bodyValue(jsonBody)
                     .retrieve()
                     .toBodilessEntity()
                     .block(); // Espera a resposta

            // 3. Se a chamada for bem-sucedida
            pedidoSalvo.setStatus("PROCESSADO_HTTP");

            System.out.println("Pedido " + pedidoSalvo.getId() + " criado e PROCESSADO (HTTP) pelo Warehouse.");

        } catch (Exception e) {
            // 4. Se a chamada falhar
            pedidoSalvo.setStatus("ERRO_COMUNICACAO");
            System.err.println("ERRO ao comunicar com o Warehouse. Verifique se ele está rodando na porta 8082.");
        }

        return repository.save(pedidoSalvo);
    }

    public java.util.List<Pedido> buscarTodos() {
        return repository.findAll();
    }
}