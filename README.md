# SentinelTrack

Sistema de Monitoramento e Rastreamento de Incidentes

## Equipe
- Thomaz - RM555323
- Vinicius - RM556089
- Gabriel - RM556972

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

## Requisitos
- JDK 17 ou superior
- Maven 3.6 ou superior
- Oracle Database 19c ou superior

## Configuração do Banco de Dados
1. Acesse o Oracle SQL Developer ou outro cliente SQL
2. Conecte-se ao banco de dados usando as credenciais:
   - Host: oracle.fiap.com.br
   - Port: 1521
   - SID: ORCL
   - Usuário: RM556089
   - Senha: 290305
3. Execute o script SQL localizado em `src/main/resources/db/script.sql`

## Executando o Projeto
1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o comando:
```bash
mvn spring-boot:run
```
4. Acesse a documentação Swagger em: http://localhost:8080/swagger-ui.html

## Estrutura do Projeto
- `src/main/java/br/com/fiap/sentineltrack/`
  - `controller/` - Controladores REST
  - `service/` - Lógica de negócios
  - `repository/` - Repositórios JPA
  - `entity/` - Entidades JPA
  - `dto/` - Objetos de transferência de dados
  - `exception/` - Tratamento de exceções
  - `config/` - Configurações do Spring

## Funcionalidades
- CRUD completo de Usuários, Locais, Ativos e Incidentes
- Relacionamentos entre entidades
- Validação de campos
- Paginação e ordenação de resultados
- Cache para otimização
- Documentação Swagger
- Tratamento centralizado de erros

## Scripts PL/SQL
O projeto inclui dois blocos PL/SQL:
1. Análise de incidentes por local com agrupamento
2. Análise de severidade de incidentes com referência a linhas anteriores/próximas

## Contribuição
Para contribuir com o projeto:
1. Faça um fork do repositório
2. Crie uma branch para sua feature
3. Faça commit das alterações
4. Faça push para a branch
5. Abra um Pull Request
