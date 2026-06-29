<div align="center">

# 🛒 ShopCard API

### Backend REST para la gestión de una tienda de cartas coleccionables

API desarrollada con **Java 17**, **Spring Boot 3** y **MySQL**, diseñada para proporcionar un backend robusto, escalable y fácil de mantener para la aplicación **ShopCard**.

<br>

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</div>

---

# 📖 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Arquitectura](#-arquitectura)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Endpoints](#-endpoints)
- [Ejemplo de uso](#-ejemplo-de-uso)
- [Testing](#-testing)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Roadmap](#-roadmap)
- [Autor](#-autor)
- [Licencia](#-licencia)

---

# ✨ Características

✅ CRUD completo de cartas coleccionables.

✅ Arquitectura RESTful.

✅ Persistencia mediante Spring Data JPA.

✅ Base de datos MySQL.

✅ Búsqueda dinámica por:

- Nombre
- Rareza
- Categoría

✅ Código organizado por capas.

✅ Fácil integración con aplicaciones Frontend.

---

# 🚀 Tecnologías

| Tecnología | Uso |
|------------|-----|
| Java 17 | Lenguaje principal |
| Spring Boot 3 | Framework Backend |
| Spring Data JPA | Persistencia |
| Hibernate | ORM |
| MySQL 8 | Base de datos |
| Maven | Gestión del proyecto |

---

# 🏗 Arquitectura

```
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL
```

La aplicación sigue una arquitectura por capas para separar responsabilidades y facilitar el mantenimiento.

---

# 📦 Instalación

## Clonar el proyecto

```bash
git clone https://github.com/Dangelcrack/ShopCardApi.git

cd ShopCardApi
```

## Crear la base de datos

```sql
CREATE DATABASE shopcard;
```

## Configurar la conexión

Editar:

```
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopcard
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Ejecutar

```bash
mvn spring-boot:run
```

o

```bash
./mvnw spring-boot:run
```

La API estará disponible en

```
http://localhost:8080
```

---

# ⚙ Configuración

Requisitos:

- Java 17+
- Maven 3.8+
- MySQL 8+

---

# 🔗 Endpoints

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| GET | `/api/productos` | Obtener todos los productos |
| GET | `/api/productos/{id}` | Obtener un producto |
| POST | `/api/productos` | Crear un producto |
| PUT | `/api/productos/{id}` | Actualizar un producto |
| DELETE | `/api/productos/{id}` | Eliminar un producto |

---

# 💻 Ejemplo de uso

### Crear una carta

### POST

```
/api/productos
```

Body

```json
{
  "nombre": "Blue-Eyes White Dragon",
  "categoria": "Monstruo",
  "rareza": "Ultra Rare",
  "precio": 49.99,
  "stock": 10
}
```

Respuesta

```json
{
  "id": 1,
  "nombre": "Blue-Eyes White Dragon",
  "categoria": "Monstruo",
  "rareza": "Ultra Rare",
  "precio": 49.99,
  "stock": 10
}
```

---

# 🧪 Testing

Actualmente se encuentra en desarrollo la implementación de pruebas unitarias utilizando:

- JUnit 5
- Mockito

---

# 📁 Estructura del proyecto

```
src
└── main
    ├── java
    │   └── com.github.dangelcrack
    │       ├── controller
    │       ├── model
    │       ├── repository
    │       ├── service
    │       └── ShopCardApiApplication.java
    │
    └── resources
        ├── application.properties
```

---

# 📈 Roadmap

- [x] CRUD de productos
- [x] Persistencia con MySQL
- [x] API REST
- [ ] Validaciones con Bean Validation
- [ ] Manejo global de excepciones
- [ ] Swagger / OpenAPI
- [ ] Docker
- [ ] Pruebas unitarias
- [ ] Integración continua (GitHub Actions)
- [ ] Autenticación con Spring Security + JWT

---

# 👨‍💻 Autor

## Ángel Guerrero

Backend Developer

GitHub

https://github.com/Dangelcrack

Correo

angelguerrero540@gmail.com

---

# ⭐ Si este proyecto te resulta útil...

No olvides dejar una **⭐ Star** en el repositorio.

---

# 📄 Licencia

Este proyecto está bajo la licencia **MIT**.
