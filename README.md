# Microsserviços E-commerce (Comunicação Síncrona - HTTP)

## Descrição do Projeto
Este projeto consiste em uma arquitetura de microsserviços desenvolvida em Java com Spring Boot, separando as funcionalidades de um e-commerce em dois serviços principais:
1.  **Storefront (Vitrine):** Responsável por criar pedidos e iniciar o fluxo de processamento.
2.  **Warehouse (Armazém):** Responsável por gerenciar o estoque e processar a baixa de produtos.

### Decisão Técnica: Comunicação Síncrona (HTTP)
O projeto original incluía mensageria assíncrona via RabbitMQ. Devido a problemas de ambiente e compatibilidade de hardware, a comunicação foi otimizada e implementada utilizando apenas a **Comunicação Síncrona via HTTP REST**.

* **Tecnologia:** O microsserviço `storefront` utiliza o **WebClient (Spring WebFlux)** para fazer requisições **POST** para o `warehouse`.
* **Fluxo:** Ao criar um pedido (`POST /pedidos` no Storefront), o Storefront faz uma chamada direta (`POST /processar-pedido`) para o Warehouse, que realiza a baixa do estoque.

## Tecnologias Utilizadas
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Comunicação:** HTTP REST (utilizando WebFlux/WebClient)
* **Banco de Dados:** H2 Database (em memória)
* **Build Tool:** Apache Maven

## Como Executar o Projeto
1.  **Iniciar o Warehouse:** Executar a classe `WarehouseApplication.java` (inicia na porta **8082**).
2.  **Iniciar o Storefront:** Executar a classe `StorefrontApplication.java` (inicia na porta **8081**).
3.  **Testes (Usando REST Client ou Postman):**
    * **1. Iniciar Estoque (Warehouse):**
        ```http
        POST http://localhost:8082/produtos
        Content-Type: application/json
        { "nome": "SmartWatch X", "quantidadeEstoque": 12 }
        ```
    * **2. Criar Pedido (Storefront - Inicia a Comunicação):**
        ```http
        POST http://localhost:8081/pedidos
        Content-Type: application/json
        { "produtoNome": "SmartWatch X", "quantidade": 5 }
        ```

---

