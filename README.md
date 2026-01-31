# Mini Ecommerce Platform
A fullstack mini e-commerce system built to refresh and demonstrate **modern Java (17+), Spring Boot 4.x, and ReactJS**

This project focuses on:
- Clean architecture
- Transaction correctness
- JWT-based authentication
- Dockerized local development

# Technologies and Frameworks

- Java 17
- Spring Boot 4
- Spring Security 7 (JWT)
- ReactJS
- Docker & Docker compose
- PostgreSQL

## Functional Scope

- User registration & login (JWT authentication)
- Product management (create, list)
- Secured REST APIs

# Getting started with Docker compose on local machine

1. Get the latest code on the main branch
2. Create the `.env` file at the project root
```dotenv
POSTGRES_DB="the application database name"
POSTGRES_USER="the user to connect with postgresql database"
POSTGRES_PASSWORD="the password of the user"
```
3. Stay at the root directory, run docker compose file to start the application
```shell
docker compose up -d
```
4. Stop the application, run this command below
```shell
docker compose down
```
5. Verify the API endpoints with Postman
