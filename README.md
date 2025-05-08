# 📦 Sistema de Gerenciamento de Produtos (Java + Swing)

Um sistema desktop para cadastro, edição e exclusão de produtos com persistência em **JSON**. Desenvolvido em Java com interface Swing, seguindo boas práticas de Clean Code e Design Patterns.

## 🚀 Funcionalidades

- **CRUD Completo**  
  ✔️ Cadastro, edição, visualização e exclusão de produtos  
  ✔️ Validação de campos (nome, valor numérico)  

- **Persistência**  
  📂 **JSON**: Dados salvos em `produtos.json` (simples e portátil)   

- **Interface Intuitiva**  
  🖥️ Tela de listagem com ordenação por preço  
  ✏️ Formulário dedicado para cadastro/edição  

- **Arquitetura Organizada**  
  🧱 Arquitetura inspirada em MVC (Model-View-Controller), separando responsabilidades de forma organizada.  
  🔒 Singleton para `ProdutoRepository`  

## ⚙️ Tecnologias

| Componente       | Tecnologia           |
|------------------|----------------------|
| Linguagem        | Java 8+              |
| Interface        | Java Swing           |
| Persistência     | JSON (Gson)          |
| Design Patterns  | Singleton, Repository|

## 📥 Instalação

1. **Pré-requisitos**:  
   - JDK 8 ou superior  
   - Maven

2. **Clone o repositório**:
   ```bash
   git clone https://github.com/EmesonNS/sistema-produtos-java.git

## 🗂️ Estrutura do Projeto
```bash
src/main/java
├── modelo/
│   ├── Produto.java            # Classe modelo do produto
│   └── ProdutoRepository.java  # Gerencia persistência (Singleton)
├── tela/
│   ├── ProdutoList.java        # Tela principal com listagem
│   └── ProdutoForm.java        # Formulário de cadastro/edição
└── Play.java                   # Classe principal (main)
```

## 🖼️ Screenshots

| Tela de Listagem                                                                          | Formulário de Cadastro                                                                    |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/d46b51af-b2b5-4214-8475-2c1a11675ae8) | ![image](https://github.com/user-attachments/assets/d254790b-18bb-4b2a-b234-601c2c38f52f) |



