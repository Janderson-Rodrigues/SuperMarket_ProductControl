# 📦 StockCycle - Monitor de Validade Inteligente

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

O **StockCycle** é uma API RESTful desenvolvida para auxiliar pequenos varejistas no controle de estoque, com foco principal na redução de desperdício por vencimento de produtos.

## 🎯 O Problema
Pequenos comércios perdem dinheiro diariamente com produtos que vencem nas prateleiras sem serem notados. O StockCycle resolve isso monitorando datas de validade de lotes individuais e gerando alertas de vencimento inteligentes.

## 🚀 Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3**
* **Spring Data JPA** (Hibernate)
* **PostgreSQL**
* **Lombok**
* **Validation API**
* **Maven**

## ⚙️ Funcionalidades Principais
* **Gerenciamento de Produtos:** Cadastro, listagem, busca por ID e atualização de dados.
* **Controle de Lotes:** Registro de múltiplos lotes por produto com data de validade e quantidade específica.
* **Alertas Inteligentes:** Endpoint dedicado que filtra produtos próximos ao vencimento baseado em um intervalo de dias personalizado.
* **Rastreamento de Vencidos:** Listagem rápida de itens que já expiraram para remoção imediata das prateleiras.
* **Busca Relacional:** Capacidade de listar todos os lotes vinculados a um produto específico.

## 🏛️ Arquitetura do Projeto
O projeto segue a arquitetura em camadas clássica do Spring Boot, garantindo separação de responsabilidades:
1.  **Controller:** Camada REST que recebe as requisições HTTP e valida os DTOs.
2.  **Service:** Contém as regras de negócio (ex: lógica de cálculo de dias para alerta).
3.  **Repository:** Interface de comunicação com o Banco de Dados.
4.  **Model/Entity:** Representação das tabelas do banco de dados.

---

## 🔌 Endpoints da API

### 📦 Produtos (`/products`)

Gerenciamento do catálogo de itens.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **POST** | `/products` | Cadastra um novo produto. |
| **GET** | `/products` | Lista todos os produtos cadastrados. |
| **GET** | `/products/{id}` | Busca detalhes de um produto específico pelo UUID. |
| **PUT** | `/products/{id}` | Atualiza os dados de um produto existente. |

### 🏷️ Lotes (`/lotes`)

Gerenciamento de estoque e validade.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **POST** | `/lotes` | Cria um novo lote vinculado a um produto. |
| **GET** | `/lotes` | Lista todos os lotes do sistema. |
| **GET** | `/lotes/alerta` | Retorna lotes próximos ao vencimento. <br> **Param:** `?dias=7` (Padrão: 7 dias). |
| **GET** | `/lotes/vencidos` | Retorna apenas os lotes que já venceram. |
| **GET** | `/lotes/produto/{id}` | Lista todos os lotes de um produto específico. |

---

## 📝 Exemplos de Uso (JSON)

### 1. Cadastrar Produto
`POST /products`
```json
{
  "name": "Iogurte Natural",
  "categoria": "Laticinios"
}
```
### 2. Cadastrar Lote
`POST /lotes`
```json
{
  "productId": "d290f1ee-6c54-4b01-90e6-d701748f0851",
  "quantidade": 20,
  "dataValidade": "2024-12-25",
  "precoCusto" : 4.50
}
```
### 3. Buscar Alertas (Vencem em 10 dias)

`GET /lotes/alerta?dias=10`

## 🛠️ Como Rodar Localmente
* **Pré-requisitos** 
* **Java 21 instalado.**
* **PostgreSQL instalado e rodando.**
* **Maven (opcional, caso use o wrapper do projeto).**

## Clone o repositório:

### Bash
```
git clone https://github.com/Janderson-Rodrigues/SuperMarket_ProductControl.git](https://github.com/Janderson-Rodrigues/SuperMarket_ProductControl.git
```

### Configure o Banco de Dados: 
### Abra o arquivo
#### src/main/resources/application.properties e configure suas credenciais:

### Properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/stock_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Execute o projeto:

### Bash
```
mvn spring-boot:run
```