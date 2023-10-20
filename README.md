## Home Financial Manager Api

Api responsável por forneçer o backend do home-financial-manger.

## Requerimentos para execução local

- JDK 17
- Maven
- Spring 3
- MySql
- Docker

## Execução

Instale home-financial-manager com maven.

```bash
  mvn clean instal
```

Instale o container docker para executar o banco de dados

```bash
  docker-compose up
```

Utilize o comando para subir o serviço localment.

```bash
  mvn spring-boot:run
```

Para acessar a documentação OPENAPI (Swagger).

```bash
  http://localhost:8080/swagger-ui.html
```

## Features

building...