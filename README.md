# API Controle Financeiro

Aplicação desenvolvida para fazer o controle de suas finanças.

## Tecnologias

- Spring Framework
- Hibernate
- Docker
- MySQL

## Get Started

Configure as variáveis de ambiente, por padrão o banco está configurado para utilizar o H2.

```
DB_ULR=jdbc:mysql://localhost:3306/financasdb
DB_USERNAME=jjuser
DB_PASSWORD=passwordfinancas
DB_DRIVER=com.mysql.cj.jdbc.Driver
DB_DIALECT=org.hibernate.dialect.MySQL8Dialect
```

### Docker

Para iniciar o container do banco basta rodar no terminal os seguintes comandos:

``` bash
docker-compose build
docker-compose up -d
```

## Endpoints 

Para acessar a collection do Postman e testar a API acesse o link: [Collection Postman](./resources/)

### Receitas
- POST /receitas
- GET /receitas
- GET /receitas/{id}
- PUT /receitas/{id}

### Despesas
- POST /despesas
- GET /despesas
- GET /despesas/{id}
- PUT /despesas/{id}

### Resumo
- GET /resumo/{ano}/{mes}

---
Desenvolvida por [Jean Jacques](https://github.com/jjeanjacques10)