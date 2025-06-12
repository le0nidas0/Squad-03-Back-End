# Squad03 - Sistema de Gerenciamento de Contratos

Este projeto é um **Sistema de Gerenciamento de Contratos** desenvolvido com **Spring Boot**. Ele integra autenticação **JWT (JSON Web Token)** para segurança, utiliza **MySQL** como banco de dados e oferece suporte para execução via **Maven** e **Docker**, facilitando o desenvolvimento e a implantação.

---

## 🛠 Pré-requisitos

Para rodar a aplicação, você precisará ter os seguintes softwares instalados:

* **Java Development Kit (JDK) 17+**
* **Apache Maven 3.8+**
* **MySQL Server 8+**
* **Docker** (opcional, para rodar o banco ou a aplicação em containers)

---

## ⚙️ Configuração do Banco de Dados

Você pode configurar e rodar o banco de dados de duas maneiras:

### 🔸 Opção 1: Banco MySQL Local

1.  **Crie o banco de dados** no seu servidor MySQL local:

    ```sql
    CREATE DATABASE squad03;
    ```

2.  **Atualize o arquivo `src/main/resources/application.properties`** com as suas credenciais e URL do banco:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/squad03
    spring.datasource.username=SEU_USUARIO
    spring.datasource.password=SUA_SENHA
    spring.jpa.hibernate.ddl-auto=update
    ```

### 🔸 Opção 2: Banco com Docker

1.  **Execute o seguinte comando** para subir um container MySQL com Docker:

    ```bash
    docker run --name squad03-mysql -e MYSQL_ROOT_PASSWORD=admin123 -e MYSQL_DATABASE=squad03 -p 3306:3306 -d mysql:8.0
    ```

2.  **Atualize o arquivo `src/main/resources/application.properties`** conforme as credenciais do Docker:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/squad03
    spring.datasource.username=root
    spring.datasource.password=admin123
    spring.jpa.hibernate.ddl-auto=update
    ```

---

## 🚀 Rodando a Aplicação

Escolha uma das opções abaixo para iniciar a aplicação:

### 🔸 Opção 1: Executar Localmente via Maven

No diretório raiz do projeto, execute o comando Maven:

```bash
mvn spring-boot:run
A aplicação estará disponível em:
http://localhost:8080

🔸 Opção 2: Executar via Docker
Construa a imagem Docker da aplicação no diretório raiz do projeto:


docker build -t squad03-app .
Rode o container da aplicação, linkando-o ao container do banco de dados (se você usou a Opção 2 para o banco):


docker run -p 8080:8080 --name squad03 --link squad03-mysql:db squad03-app
Observação: O parâmetro --link squad03-mysql:db conecta a aplicação ao container MySQL rodando sob o nome squad03-mysql, permitindo que a aplicação acesse o banco de dados através do nome de host db.

🔐 Endpoints de Autenticação
O sistema oferece os seguintes endpoints para gerenciamento de usuários e autenticação:

Registrar novo usuário:
POST /auth/register

Realizar login:
POST /auth/login

O endpoint de login retornará um token JWT. Para acessar as rotas protegidas da API, envie este token no cabeçalho Authorization de suas requisições:


Authorization: Bearer <seu_token>
📦 Tecnologias Utilizadas
Este projeto foi desenvolvido utilizando as seguintes tecnologias:

Java 17
Spring Boot 3.4.4
Spring Security (com autenticação JWT)
JPA / Hibernate
MySQL
Docker
Maven
