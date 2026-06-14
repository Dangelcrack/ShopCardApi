# 🛒 ShopCard API — Backend

API REST para la gestión de una tienda de cartas coleccionables, desarrollada con **Spring Boot** y **MySQL**. Actúa como backend del sistema [ShopCardApplication](https://github.com/Dangelcrack/ShopCardApplication).

---

## ✨ Características

- 📦 **CRUD completo** de productos (cartas)
- 🔍 **Búsqueda y filtrado** por nombre, rareza y tipo
- 🗄️ **Persistencia** con MySQL 8
- 🌐 **API RESTful** con endpoints documentados
- ⚙️ **Spring Boot** con Maven para gestión de dependencias

---

## 🛠️ Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17+ | Lenguaje principal |
| Spring Boot | — | Framework backend |
| Spring Data JPA | — | Persistencia y ORM |
| MySQL | 8 | Base de datos |
| Maven | 3.8+ | Gestión de dependencias |

---

## ▶️ Instalación y ejecución

### Requisitos previos
- Java 17 o superior
- Maven 3.8+
- MySQL 8+

```bash
# 1. Clonar el repositorio
git clone https://github.com/Dangelcrack/ShopCardApi.git
cd ShopCardApi

# 2. Crear la base de datos
mysql -u root -p
CREATE DATABASE shopcard;

# 3. Configurar src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopcard
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# 4. Compilar y ejecutar
mvn spring-boot:run
```

La API arranca en `http://localhost:8080`

---

## 🔗 Endpoints principales

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/productos` | Listar todas las cartas |
| GET | `/api/productos/{id}` | Obtener carta por ID |
| POST | `/api/productos` | Crear nueva carta |
| PUT | `/api/productos/{id}` | Actualizar carta |
| DELETE | `/api/productos/{id}` | Eliminar carta |

---

## 📁 Estructura del proyecto

```
ShopCardApi/
├── src/main/java/
│   └── com/github/dangelcrack/
│       ├── controller/     # Endpoints REST
│       ├── model/          # Entidades JPA
│       ├── repository/     # Repositorios Spring Data
│       └── service/        # Lógica de negocio
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

---

## 🔄 Proyecto relacionado

Este backend funciona junto al frontend Angular:
👉 [ShopCardApplication](https://github.com/Dangelcrack/ShopCardApplication)

---

## 👤 Autor

**Ángel Guerrero** — [@Dangelcrack](https://github.com/Dangelcrack)

📧 angelguerrero540@gmail.com

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT.
