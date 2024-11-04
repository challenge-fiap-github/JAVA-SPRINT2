
# Projeto OdontoVision - Challenge Odontoprev

## Visão Geral

Este projeto é uma aplicação Spring Boot desenvolvida para a **OdontoVision** como parte do Challenge Odontoprev. O objetivo principal é reduzir a ocorrência de sinistros em atendimentos odontológicos por meio de incentivos preventivos e monitoramento proativo, utilizando IA para identificar possíveis fraudes.

## Funcionalidades

- **Gamificação**: Incentivo ao paciente para consultas preventivas e bons hábitos de higiene bucal, com sistema de pontuação e recompensas.
- **Registro de Diagnósticos**: Histórico detalhado dos diagnósticos para cada paciente.
- **Análise Preditiva**: Uso de IA para detectar padrões de comportamento anômalos que indicam uso excessivo ou possíveis fraudes.
- **Sistema de Pontuação e Recompensas**: Acúmulo de pontos em consultas preventivas e resgate de recompensas.

## Tecnologias Utilizadas

- **Spring Boot 3.3.5**: Framework Java para o backend.
- **Banco de Dados**: Oracle (produção) e H2 (testes).
- **Segurança**: Spring Security para autenticação e autorização.
- **API RESTful**: Seguindo boas práticas e nível 3 de maturidade REST (HATEOAS).
- **Lombok**: Redução de verbosidade do código.
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

    <!-- Lombok para reduzir verbosidade -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
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

Após a transição para o ambiente de produção, a aplicação será configurada para utilizar o banco de dados **Oracle**.

### Acessando o Console do H2

Para acessar o console do H2 e visualizar os dados, inicie a aplicação e navegue até:
- [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- Use a URL do banco de dados `jdbc:h2:mem:testdb` e as credenciais padrão (`username`: `sa`, `password`: vazio).

## Endpoints REST Principais

1. **Usuário**
    - `POST /api/usuarios`: Cadastro de novos usuários.
    - `POST /api/usuarios/login`: Autenticação de usuários.

2. **Consulta**
    - `POST /api/consultas`: Agendamento de consultas.
    - `GET /api/consultas/{id}`: Detalhes de uma consulta.

3. **Pontuação**
    - `GET /api/pontuacao/{usuarioId}`: Consulta da pontuação acumulada.
    - `POST /api/pontuacao/bonus`: Aplica pontos bônus após validação.

4. **Recompensas**
    - `GET /api/recompensas`: Lista de recompensas disponíveis.
    - `POST /api/recompensas/resgate`: Resgate de recompensas.

## Estrutura de Camadas e Boas Práticas

Este projeto utiliza uma arquitetura em camadas para promover a separação de responsabilidades e a modularidade do código.

- **Camada de Controller**: Expondo endpoints e lidando com requisições HTTP.
- **Camada de Serviço**: Contendo a lógica de negócios e validações.
- **Camada de Repositório**: Acesso ao banco de dados e operações de CRUD com JPA.

## Executando a Aplicação

Para executar a aplicação localmente, siga estes passos:

1. Clone o repositório.
2. Navegue até a pasta do projeto.
3. Execute o seguinte comando:

   ```bash
   ./mvnw spring-boot:run
   ```

4. A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).
