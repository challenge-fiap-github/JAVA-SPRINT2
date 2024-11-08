
# Projeto OdontoVision - Challenge Odontoprev

## Visão Geral

Este projeto é uma aplicação Spring Boot desenvolvida para a **OdontoVision** como parte do Challenge Odontoprev. O objetivo principal é reduzir a ocorrência de sinistros em atendimentos odontológicos por meio de incentivos preventivos e monitoramento proativo, utilizando IA para identificar possíveis fraudes.

## Evolução com base na Sprint 1 de Java Advanced

Nessa Sprint alteramos as regras de negócio e unificamos o banco de dados, por tanto foi necessário refazer o código java.

## Funcionalidades

- **Gamificação**: Incentivo ao paciente para consultas preventivas e bons hábitos de higiene bucal, com sistema de pontuação e recompensas.
- **Registro de Diagnósticos**: Histórico detalhado dos diagnósticos para cada paciente.
- **Análise Preditiva**: Uso de IA para detectar padrões de comportamento anômalos que indicam uso excessivo ou possíveis fraudes.
- **Sistema de Pontuação e Recompensas**: Acúmulo de pontos em consultas preventivas e resgate de recompensas.

## Tecnologias Utilizadas

- **Spring Boot 3.3.5**: Framework Java para o backend.
- **Banco de Dados**: Oracle (produção) e H2 (testes).
- **Segurança**: Spring Security para autenticação e autorização. (Decidimos por deixar esse ponto para a Sprint 3)
- **API RESTful**: Seguindo boas práticas e nível 3 de maturidade REST (HATEOAS).
- **JPA/Hibernate**: Persistência e mapeamento objeto-relacional.
- **IA**: Random Forest para análise de padrões e monitoramento de fraudes.

## Estrutura do Projeto

A aplicação foi estruturada em camadas para facilitar a organização do código e a manutenção do projeto.

### Camadas e Pacotes

- **`model`**: Contém as classes de entidade JPA, representando as tabelas do banco de dados (ex: `Usuario`, `Consulta`, `Pontuacao`).
- **`repository`**: Interfaces que estendem `JpaRepository` para acesso aos dados e operações no banco.
- **`service`**: Camada de lógica de negócios, onde as regras e funcionalidades principais são implementadas (ex: `PontuacaoService`, `ConsultaService`).
- **`controller`**: Controladores que expõem endpoints RESTful para interação com o cliente (ex: `PacienteController`, `DentistaController`).
- **`dto`**: Objetos de Transferência de Dados (Data Transfer Objects) usados para padronizar requisições e respostas da API.
- **`config`**: Configurações gerais, incluindo segurança (Spring Security) e integrações com APIs externas.
- **`exception`**: Classes para tratamento de exceções personalizadas, ajudando a lidar com erros de maneira estruturada.

## Dependências Principais (`pom.xml`)

```xml
<dependencies>
    <!-- Spring Data JDBC -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring HATEOAS -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-hateoas</artifactId>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Validação -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring DevTools para desenvolvimento -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Segurança para testes -->
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Banco de Dados H2 para testes -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.3.232</version>
    </dependency>
</dependencies>
```

## Configuração para Desenvolvimento

### Banco de Dados H2 (Modo de Teste)

Durante o desenvolvimento, o banco de dados **H2** é utilizado para testes. Abaixo está um exemplo de configuração para o H2 no arquivo `application.properties`.

```properties
# Configurações do banco de dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

Para o ambiente de produção, a aplicação será configurada para utilizar o banco de dados **Oracle**. A configuração esperada no `application.properties` para Oracle seria semelhante a esta:

```properties
spring.datasource.url=jdbc:oracle:thin:@//host:port/service
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.username=YOUR_ORACLE_USERNAME
spring.datasource.password=YOUR_ORACLE_PASSWORD
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect
```

### Acessando o Console do H2

Para acessar o console do H2 e visualizar os dados, inicie a aplicação e navegue até:
- [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- Use a URL do banco de dados `jdbc:h2:mem:testdb` e as credenciais padrão (`username`: `sa`, `password`: vazio).

## Endpoints REST

Para documentar todos os endpoints para o README, aqui está uma estrutura que inclui os principais endpoints com as descrições básicas de cada um. Vou considerar os nomes convencionais dos endpoints com base nos controladores recebidos.

API Documentation

A seguir está a lista de endpoints disponíveis na API da OdontoVision.

Usuarios

Registro de usuário

	•	POST /api/usuarios/registrar
	•	Descrição: Cadastra um novo usuário.
	•	Corpo da requisição: JSON com as informações do usuário.

Autenticação de usuário

	•	POST /api/usuarios/autenticar
	•	Descrição: Autentica um usuário.
	•	Corpo da requisição: JSON com email e senha.

Obter usuário por ID

	•	GET /api/usuarios/{id}
	•	Descrição: Obtém os detalhes de um usuário específico pelo ID.

Atualizar usuário

	•	PUT /api/usuarios/{id}
	•	Descrição: Atualiza as informações de um usuário específico pelo ID.
	•	Corpo da requisição: JSON com as novas informações do usuário.

Deletar usuário

	•	DELETE /api/usuarios/{id}
	•	Descrição: Remove um usuário pelo ID.

Pontuacoes

Listar pontuações de um usuário

	•	GET /api/pontuacoes/usuario/{usuarioId}
	•	Descrição: Lista todas as pontuações de um usuário específico.

Obter pontuação por ID

	•	GET /api/pontuacoes/{id}
	•	Descrição: Obtém os detalhes de uma pontuação específica pelo ID.

Registrar nova pontuação

	•	POST /api/pontuacoes/usuario/{usuarioId}
	•	Descrição: Registra uma nova pontuação para um usuário específico.
	•	Corpo da requisição: JSON com os detalhes da pontuação.

Recompensas

Listar recompensas

	•	GET /api/recompensas
	•	Descrição: Lista todas as recompensas.

Obter recompensa por ID

	•	GET /api/recompensas/{id}
	•	Descrição: Obtém detalhes de uma recompensa específica pelo ID.

Registrar nova recompensa

	•	POST /api/recompensas
	•	Descrição: Cadastra uma nova recompensa.
	•	Corpo da requisição: JSON com os detalhes da recompensa.

Conquistas

Listar conquistas

	•	GET /api/conquistas
	•	Descrição: Lista todas as conquistas.

Obter conquista por ID

	•	GET /api/conquistas/{id}
	•	Descrição: Obtém detalhes de uma conquista específica pelo ID.

Registrar nova conquista

	•	POST /api/conquistas
	•	Descrição: Cadastra uma nova conquista.
	•	Corpo da requisição: JSON com os detalhes da conquista.

HistoricoPontuacao

Listar histórico de pontuação

	•	GET /api/historicoPontuacao
	•	Descrição: Lista o histórico de pontuação.

Obter histórico de pontuação por ID

	•	GET /api/historicoPontuacao/{id}
	•	Descrição: Obtém detalhes de um histórico de pontuação específico pelo ID.

Registrar histórico de pontuação

	•	POST /api/historicoPontuacao
	•	Descrição: Cadastra um novo histórico de pontuação.
	•	Corpo da requisição: JSON com os detalhes do histórico.

LogAtividade

Listar logs de atividade

	•	GET /api/logAtividade
	•	Descrição: Lista todos os logs de atividade.

Obter log de atividade por ID

	•	GET /api/logAtividade/{id}
	•	Descrição: Obtém detalhes de um log de atividade específico pelo ID.

LogFraude

Listar logs de fraude

	•	GET /api/logFraude
	•	Descrição: Lista todos os logs de fraude.

Obter log de fraude por ID

	•	GET /api/logFraude/{id}
	•	Descrição: Obtém detalhes de um log de fraude específico pelo ID.

Niveis

Listar níveis

	•	GET /api/niveis
	•	Descrição: Lista todos os níveis.

Obter nível por ID

	•	GET /api/niveis/{id}
	•	Descrição: Obtém detalhes de um nível específico pelo ID.

Registrar novo nível

	•	POST /api/niveis
	•	Descrição: Cadastra um novo nível.
	•	Corpo da requisição: JSON com os detalhes do nível.

Sinistros

Listar sinistros

	•	GET /api/sinistros
	•	Descrição: Lista todos os sinistros.

Obter sinistro por ID

	•	GET /api/sinistros/{id}
	•	Descrição: Obtém detalhes de um sinistro específico pelo ID.

Registrar novo sinistro

	•	POST /api/sinistros
	•	Descrição: Cadastra um novo sinistro.
	•	Corpo da requisição: JSON com os detalhes do sinistro.

ValidacaoChecklist

Listar validações de checklist

	•	GET /api/validacaoChecklist
	•	Descrição: Lista todas as validações de checklist.

Obter validação de checklist por ID

	•	GET /api/validacaoChecklist/{id}
	•	Descrição: Obtém detalhes de uma validação de checklist específica pelo ID.

Registrar nova validação de checklist

	•	POST /api/validacaoChecklist
	•	Descrição: Cadastra uma nova validação de checklist.
	•	Corpo da requisição: JSON com os detalhes da validação.

Cada endpoint permite que a aplicação interaja com as entidades principais da API, fornecendo as funcionalidades de CRUD necessárias para gerenciamento.

## Arquitetura RESTful com HATEOAS

A aplicação implementa o nível 3 de maturidade REST, utilizando HATEOAS. Os controladores adicionam links relevantes a cada recurso, permitindo navegação estruturada pela API. Por exemplo, ao listar conquistas de um usuário, o recurso inclui links para detalhes específicos de cada conquista e links para outros endpoints relacionados.

## Lombok: Uso Opcional

Lombok foi considerado para reduzir a verbosidade do código, mas, devido a problemas na resolução de métodos ao rodar `mvn clean install`, a equipe optou por manter o projeto sem Lombok. Assim, getters, setters e construtores foram implementados manualmente para garantir estabilidade e compatibilidade do código.

## Controle de Versão e Acesso

Todo o

código está versionado no GitHub e acessível aos professores, conforme solicitado nos requisitos.

## Cronograma da Sprint 2 (14/10/2024 - 08/11/2024)

### Semana 1 (14/10 - 18/10)
**Planejamento e Alinhamento Inicial:**
- **14/10 (Segunda-feira)**: Reunião inicial para alinhamento dos objetivos da sprint e definição das funcionalidades principais.
- **15/10 (Terça-feira)**: Discussão sobre integração entre banco de dados e IA (Matheus e Luis). Configuração do ambiente de DevOps (Sabrina).

**Atividades de Desenvolvimento:**
- **16/10 (Quarta-feira)**: Configuração inicial do banco de dados e IA.
- **17/10 (Quinta-feira)**: Configuração da pipeline de integração contínua (Sabrina).
- **18/10 (Sexta-feira)**: Criação dos endpoints para o app mobile (Luis). Revisão de infraestrutura para escalabilidade no Azure (Sabrina).

### Semana 2 (21/10 - 25/10)
**Desenvolvimento e Integração:**
- **21/10 (Segunda-feira)**: Desenvolvimento da lógica de IA (Matheus) e revisão de código (Sabrina).
- **22/10 (Terça-feira)**: Integração da IA ao banco de dados (Luis) e automação de implantação (Sabrina).
- **23/10 (Quarta-feira)**: Finalização do treinamento da IA e primeiros testes com dados reais.
- **24/10 (Quinta-feira)**: Ajustes na arquitetura do banco e infraestrutura.
- **25/10 (Sexta-feira)**: Testes de carga e stress.

### Semana 3 (28/10 - 01/11)
**Testes e Correções:**
- **28/10 (Segunda-feira)**: Testes em ambiente de desenvolvimento e ajustes na IA.
- **29/10 (Terça-feira)**: Testes das funcionalidades do aplicativo mobile.
- **30/10 (Quarta-feira)**: Automatização do processo de validação dos diagnósticos por IA.
- **31/10 (Quinta-feira)**: Testes finais de QA.
- **01/11 (Sexta-feira)**: Revisão geral e preparação para entrega.

### Semana 4 (04/11 - 08/11)
**Entrega e Documentação:**
- **04/11 (Segunda-feira)**: Documentação do banco de dados e endpoints (Luis).
- **05/11 (Terça-feira)**: Revisão da documentação e conformidade para produção.
- **06/11 (Quarta-feira)**: Reunião de validação dos objetivos da sprint.
- **07/11 (Quinta-feira)**: Ajustes finais no DevOps e implantação.
- **08/11 (Sexta-feira)**: Ajustes Necessários e entrega no portal da FIAP.

## Executando a Aplicação

Para executar a aplicação localmente, siga estes passos:

1. Clone o repositório.
2. Navegue até a pasta do projeto.
3. Execute o seguinte comando:

   ```bash
   ./mvnw spring-boot:run
   ```

4. A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

