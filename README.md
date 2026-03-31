# 🏷️ Sistema de Leilões

Sistema desktop desenvolvido em Java com interface gráfica Swing para gerenciamento de produtos em leilão, permitindo cadastro, listagem e venda de produtos.

---

## 📋 Sobre o Projeto

Este projeto foi desenvolvido como atividade prática do curso **Técnico de Desenvolvimento de Sistemas** do **SENAC**, com o objetivo de aplicar conceitos de:

- Desenvolvimento de interfaces gráficas com **Java Swing**
- Conexão e manipulação de banco de dados com **JDBC + MySQL**
- Arquitetura em camadas com padrão **DAO/DTO**
- Versionamento de código com **Git/GitHub**

---

## ✅ Funcionalidades

- 📦 **Cadastrar Produto** — cadastra um novo produto com nome e valor
- 📋 **Listar Produtos** — exibe todos os produtos cadastrados em uma tabela
- 🔨 **Vender Produto** — atualiza o status de um produto para "Vendido"
- 🧾 **Listar Produtos Vendidos** — exibe apenas os produtos com status "Vendido"
- 🔀 **Navegação entre telas** — tela de cadastro, listagem e vendas

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de camadas **DAO/DTO**:

```
📁 LeiloesTDSat
├── conectaDAO.java     → Conexão com o banco de dados MySQL
├── ProdutosDTO.java    → Modelo de dados do produto (id, nome, valor, status)
├── ProdutosDAO.java    → Operações no banco (INSERT, SELECT, UPDATE)
├── cadastroVIEW.java   → Tela de cadastro de produtos
├── listagemVIEW.java   → Tela de listagem e venda de produtos
└── vendasVIEW.java     → Tela de produtos vendidos
```

### Como as camadas se comunicam:

```
VIEW (tela)
   ↕
DTO (transporta os dados)
   ↕
DAO (executa os comandos no banco)
   ↕
MySQL (armazena os dados)
```

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|---|---|
| Java | Linguagem principal do projeto |
| Java Swing | Biblioteca para interface gráfica |
| JDBC | Conexão Java com banco de dados |
| MySQL | Banco de dados relacional |
| Apache NetBeans | IDE de desenvolvimento |
| Git/GitHub | Versionamento de código |

---

## 🗄️ Banco de Dados

### Criação do banco:

```sql
CREATE DATABASE IF NOT EXISTS LeiloesTDSat;

USE LeiloesTDSat;

CREATE TABLE produtos (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nome   VARCHAR(100) NOT NULL,
    valor  INT          NOT NULL,
    status VARCHAR(50)  NOT NULL
);
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos:

- Java JDK instalado
- MySQL instalado e rodando
- Apache NetBeans instalado
- Connector/J (driver JDBC do MySQL) adicionado às Libraries do projeto

### Passo a passo:

1. **Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/LeiloesTDSat.git
```

2. **Crie o banco de dados** executando o script SQL acima no MySQL Workbench

3. **Configure a conexão** no arquivo `conectaDAO.java`:
```java
conn = DriverManager.getConnection(
    "jdbc:mysql://localhost/LeiloesTDSat?user=root&password=SUA_SENHA"
);
```

4. **Abra o projeto** no Apache NetBeans

5. **Execute o projeto** clicando em Run ou pressionando `F6`

---

## 📂 Versionamento

O projeto utilizou duas branches:

| Branch | Descrição |
|---|---|
| `main` | Versão estável do projeto |
| `melhorias` | Branch de desenvolvimento das novas funcionalidades |

## 👨‍💻 Autor

Desenvolvido por **Igor Pereira**  
Curso Técnico de Desenvolvimento de Sistemas — **SENAC**
