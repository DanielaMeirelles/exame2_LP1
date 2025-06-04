# 📑 Exame Prático 2 - Linguagem de Programação

Este projeto foi desenvolvido como parte do **Exame Prático 2** da disciplina **Linguagem de Programação**.

O objetivo principal foi aplicar conceitos de **orientação a objetos**, utilizando a linguagem **Java**, o gerenciador de dependências **Maven**, a biblioteca gráfica **JavaFX**, e a integração com um **banco de dados MySQL** por meio de classes DAO (Data Access Object) para construir uma aplicação estruturada com **10 classes distintas**.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- JavaFX
- Maven
- MySQL
- JDBC (Driver Connector/J)
- IDE recomendada: IntelliJ IDEA

---

## 📚 Estrutura do Projeto

A aplicação foi organizada de maneira modular, seguindo boas práticas de **programação orientada a objetos**. As **10 classes** foram implementadas com responsabilidades distintas, garantindo **clareza**, **manutenção** e **reutilização de código**.

**Exemplos de classes criadas:**

- `App` – classe principal que inicia a aplicação JavaFX.
- `Controller` – responsável por gerenciar as interações da interface.
- `PessoaDAO` e `FlorDAO` – classes DAO responsáveis pelas operações de persistência no banco de dados MySQL.
- `Conexao` – classe utilitária para estabelecer a conexão com o banco de dados.

---

## 🗄️ Integração com Banco de Dados MySQL

A aplicação utiliza um banco de dados **MySQL**, acessado via **JDBC** e encapsulado através de classes **DAO** para realizar operações **CRUD (Create, Read, Update, Delete)** de maneira segura e organizada.

O arquivo `Conexao.java` é responsável por gerenciar a conexão com o banco de dados, enquanto as classes DAO implementam os métodos necessários para manipular os dados das entidades **Pessoa** e **Flor**.

---

## ▶️ Como Executar

### 1️⃣ Clone este repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```
### 2️⃣ Configure o banco de dados MySQL

- Certifique-se de ter o MySQL instalado e em execução.
- Crie um banco de dados com o nome especificado no projeto.
- Execute o script SQL fornecido (caso exista) para criar as tabelas necessárias.

### 3️⃣ Compile e execute com Maven:

```bash
mvn clean javafx:run
```
⚠️ Certifique-se de ter o Java JDK 17+ instalado e configurado corretamente, além do Maven e o MySQL configurado com a base de dados exigida pelo projeto.

## 📷 Interface Gráfica

A aplicação possui uma interface construída com JavaFX, com janelas responsivas, inputs de dados, feedback visual ao usuário e operações integradas com o banco de dados.

## 🧪 Objetivo do Exame

Este exame teve como finalidade:

- Avaliar a capacidade de estruturar um projeto Java com múltiplas classes.

- Demonstrar conhecimento de orientação a objetos (herança, composição, encapsulamento).

- Utilizar Maven para gerenciamento de dependências.

- Implementar uma interface gráfica funcional com JavaFX.

- Integrar um banco de dados MySQL utilizando o padrão DAO e JDBC.

## 📜 Licença

Este projeto foi desenvolvido apenas para fins acadêmicos.
