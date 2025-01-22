# sboot-voting

sboot-voting é uma aplicação desenvolvida em Java utilizando o framework Spring Boot para gerenciar sessões de votação. Este projeto fornece uma API RESTful para criar, gerenciar sessões de votação e registrar votos, garantindo regras de negócio como verificação de sessões encerradas e controle de votos duplicados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
    - Spring Web
    - Spring Data JPA
    - Spring Validation
- **H2 Database** (banco de dados em memória para desenvolvimento/testes)
- **Lombok** (para reduzir boilerplate de código)
- **JUnit 5** (para testes unitários)
- **Mockito** (para mock em testes)
- **Swagger/OpenAPI** (para documentação da API)

## Funcionalidades

1. **Cadastrar uma nova pauta**

2. **Abrir uma sessão de votação em uma pauta. A votação tem um tempo determinado**

3. **Receber votos associados em pautas(votos de "SIM" ou "NÃO"**

4. **Cada voto é associado a um cpf e só é possivel votar uma vez em cada sessão de votação**

5. **Contabilizar os votos e dar o resultado da votação**

6. **Regras de Negócio**
    - Impedir votos em sessões encerradas.
    - Impedir votos com o mesmo cpf.
    - Lançar exceções específicas para erros como "Sessão não encontrada", "Sessão encerrada" ou "Associado já votou".

## Instalação e Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/LeonardoSilva-code/sboot-voting.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd sboot-voting
   ```

3. Compile e execute o projeto usando Gradle:
   ```bash
   ./gradlew build
   ./gradlew run
   ```

4. Acesse a API:
    - Documentação Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - API Base URL: [http://localhost:8080/api/v1](http://localhost:8080/api/v1)

## Executando com Docker

1. Certifique-se de ter o Docker instalado em sua máquina.

2. Construa a imagem Docker:
   ```bash
   docker build -t sboot-voting .
   ```

3. Execute o container:
   ```bash
   docker run -p 8080:8080 sboot-voting
   ```

4. Acesse a API:
    - Documentação Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - API Base URL: [http://localhost:8080/api/v1](http://localhost:8080/api/v1)

## Arquitetura Hexagonal

Arquitetura Hexagonal, também conhecida como Arquitetura de Portas e Adaptadores, é um modelo de design de software proposto por Alistair Cockburn. Ela tem como objetivo criar sistemas mais modulares, flexíveis e testáveis, promovendo uma clara separação entre o núcleo da aplicação (regra de negócio) e as interações externas (como interfaces de usuário, bancos de dados ou APIs).

### Como foi aplicada:

1. **Domínio e Regras de Negócio:**
    - O diretório `core` contém as entidades (`domain`) e casos de uso (`usecase`) que representam a lógica central do sistema.

2. **Portas (Interfaces):**
    - Localizadas no pacote `ports`, as portas de entrada definem os métodos disponíveis para interagir com o sistema, enquanto as portas de saída especificam as interações externas, como persistência de dados.

3. **Adaptadores:**
    - As implementações das portas de saída, como acesso ao banco de dados ou integração com outros serviços, estão no diretório `adapters`.

Essa estrutura modular facilita a manutenção e evolução do projeto, permitindo substituição ou adição de tecnologias sem impactar a lógica principal.

## Estrutura do Projeto

```plaintext
src
├── main
│   ├── java/com/example/sboot_voting
│   │   ├── application
│   │   │   ├── config                # Configurações e exceções personalizadas
│   │   │   ├── core                  # Lógica de domínio e casos de uso
│   │   │   ├── domain                # Entidades principais
│   │   │   ├── ports                 # Interfaces para entrada/saída
│   │   └── adapters                  # Implementações das portas por adaptadores
|   |       ├── in                    # Adaptadores de entrada (controllers)
|   |       ├── out                   # Adaptadores de saída (banco de dados)
│   └── resources
│       ├── application.properties    # Configurações da aplicação
├── test                              # Testes unitários
```

## Exemplo de Uso da API

### Criar uma Sessão de Votação

**Requisição:**
```http
POST /api/v1/agenda
Content-Type: application/json

{
    "title": "Revisão do Estatuto da Organização",
    "description": "Proposta para revisar e atualizar o estatuto vigente da organização, considerando novas diretrizes de governança."
}
```

**Resposta:**
```json
{
  "id": "c67e9232-fa87-447b-af32-e728df4390b5",
  "title": "Revisão do Estatuto da Organização",
  "description": "Proposta para revisar e atualizar o estatuto vigente da organização, considerando novas diretrizes de governança.",
  "createdAt": "2025-01-21T20:46:04.1956159",
  "updatedAt": "2025-01-21T20:46:04.1956159"
}
```
## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).

---
Desenvolvido por Leonardo Silva.

