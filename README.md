
# 🛠️ SentinelTrack - API de Usuários

Este projeto faz parte do ecossistema da solução **SentinelTrack**, voltada para controle e rastreamento de motocicletas em pátios via IoT. Este serviço é responsável pelo **gerenciamento de usuários**, incluindo operações de CRUD com suporte a Swagger para documentação da API.

---

## 📦 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **OpenAPI (Swagger)**
- **OracleDB**
- **Lombok**
- **Maven**

---

## 🔁 Funcionalidades da API

A API permite:

- 🔍 Listar todos os usuários
- 🔍 Buscar usuário por:
  - `ID` (UUID)
  - `RM` (registro)
  - `Nome`
- ➕ Cadastrar um novo usuário
- ✏️ Atualizar um usuário existente
- ❌ Remover usuário por:
  - `ID`
  - `RM`

---

## 🔓 Endpoints para Testar

| Método | Endpoint                                | Descrição                         |
|--------|-----------------------------------------|-----------------------------------|
| GET    | `/users?page=0&size=5&sort=name,asc`    | Lista todos os usuários paginados |
| GET    | `/bikes?page=0&size=5&sortBy=plate`     | Lista todos as motos paginadas    |


*Lembrar de acessar a porta 8080 ao se conectar aos endpoints!*



> Todas as rotas estão documentadas no Swagger UI.

---

## 📄 Documentação Swagger

A documentação interativa estará disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- Java 17+
- Maven
- Banco de dados PostgreSQL (ou compatível)

### Rodando localmente

```bash
# Clone o repositório
git clone https://github.com/SouzaEu/SentinelTrack
cd SentinelTrack

# Compilar e rodar
./mvnw spring-boot:run
```

---

## 🧑‍💻 Participantes

- Thomaz Oliveira Vilas Boas Bartol - RM555323
- Vinicius Souza Carvalho - RM556089
- Gabriel Duarte - RM556972

---

## 📁 Estrutura do Projeto

```
src/
├── controllers/        # Camada REST (UserController)
├── services/           # Camada de negócio (UserService)
├── models/             # Entidades e DTOs
└── repositories/       # Interface de persistência
```

---

## 📬 Contato

Em caso de dúvidas, entre em contato via [rm556089@fiap.com.br] (GitHub).

---
