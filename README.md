# Foro Hub

API REST en Spring Boot para gestión de tópicos, respuestas, usuarios y cursos.

## Requisitos
Java 21, Maven, MySQL 8

## Inicio rápido
docker compose up -d
mvn spring-boot:run

## Autenticación
POST /auth/login
{
  "email": "admin@foro.com",
  "password": "password"
}

## Endpoints
GET /topicos
GET /topicos/{id}
POST /topicos
PUT /topicos/{id}
DELETE /topicos/{id}
GET /topicos/{id}/respuestas
POST /topicos/{id}/respuestas
POST /usuarios
