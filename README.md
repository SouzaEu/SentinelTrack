# SentinelTrack

Sistema de Monitoramento e Rastreamento de Incidentes

## Equipe
- Vinicius Souza Carvalho - RM556089
- Thomaz Oliveira vilas boas bartol - RM555323
- Gabriel Duarte - RM556972

## Descrição do Projeto
O SentinelTrack é um sistema de monitoramento e rastreamento de incidentes que permite o gerenciamento eficiente de ocorrências em diferentes locais. O sistema oferece funcionalidades para cadastro de usuários, locais, ativos e incidentes, além de alertas e relatórios.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- Oracle Database
- Maven
- Lombok
- Swagger/OpenAPI
- JUnit 5
- Mockito

## Requisitos
- JDK 17 ou superior
- Maven 3.6 ou superior
- Oracle Database 19c ou superior
- Git

## Configuração do Ambiente

### 1. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/sentineltrack.git
cd sentineltrack
```

### 2. Configuração do Banco de Dados
1. Acesse o Oracle SQL Developer
2. Execute o script SQL localizado em `src/main/resources/db/script.sql`

### 3. Configuração do Projeto
1. Abra o arquivo `src/main/resources/application.properties`
2. Verifique se as configurações do banco de dados estão corretas
3. Ajuste outras configurações conforme necessário

## Executando o Projeto

### 1. Compilação
```bash
mvn clean install
```

### 2. Execução
```bash
mvn spring-boot:run
```

### 3. Acessando a API
- Documentação Swagger: http://localhost:8080/swagger-ui.html
- API Base URL: http://localhost:8080/api

## Testes

### Executando os Testes
```bash
mvn test
```

### Cobertura de Testes
```bash
mvn verify
```

## Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   └── br/com/fiap/sentineltrack/
│   │       ├── controller/    # Controladores REST
│   │       ├── service/       # Lógica de negócios
│   │       ├── repository/    # Repositórios JPA
│   │       ├── entity/        # Entidades JPA
│   │       ├── dto/          # Objetos de transferência de dados
│   │       ├── exception/    # Tratamento de exceções
│   │       └── config/       # Configurações do Spring
│   └── resources/
│       ├── application.properties
│       └── db/
│           └── script.sql
└── test/
    └── java/
        └── br/com/fiap/sentineltrack/
            └── service/      # Testes unitários
```

## Funcionalidades
- CRUD completo de Usuários, Locais, Ativos e Incidentes
- Relacionamentos entre entidades
- Validação de campos com Bean Validation
- Paginação e ordenação de resultados
- Cache para otimização de requisições
- Documentação Swagger/OpenAPI
- Tratamento centralizado de erros
- Testes unitários

## Endpoints Principais
- `/api/usuarios` - Gerenciamento de usuários
- `/api/locais` - Gerenciamento de locais
- `/api/ativos` - Gerenciamento de ativos
- `/api/incidentes` - Gerenciamento de incidentes
- `/api/alertas` - Gerenciamento de alertas

## Contribuição
1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Faça commit das alterações (`git commit -m 'Adiciona nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Scripts PL/SQL
O projeto inclui dois blocos PL/SQL:
1. Análise de incidentes por local com agrupamento
2. Análise de severidade de incidentes com referência a linhas anteriores/próximas
