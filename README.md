````markdown
# 🛒 ShopCard API

API REST para la gestión de una tienda de cartas coleccionables. Desarrollada con **Java 17** y **Spring Boot**, diseñada como el backend robusto para el sistema **ShopCardApplication**.

---

## ✨ Características Principales

- 📦 **Gestión de Inventario:** CRUD completo de productos.
- 🔍 **Búsqueda Avanzada:** Filtrado dinámico por nombre, rareza y categoría.
- 🗄️ **Persistencia Robusta:** Implementación con JPA/Hibernate sobre MySQL 8.
- 🌐 **Arquitectura RESTful:** Endpoints estandarizados.
- 🛡️ **Buenas Prácticas:** Estructura modular y gestión de dependencias con Maven.

---

## 🛠️ Stack Tecnológico

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17+ | Lenguaje de programación |
| Spring Boot | 3.x | Framework de aplicación |
| Spring Data JPA | 3.x | Capa de persistencia (ORM) |
| MySQL | 8.0 | Base de datos relacional |
| Maven | 3.8+ | Gestión del ciclo de vida del proyecto |

---

## 🚀 Instalación y Ejecución

### Requisitos previos

- JDK 17 o superior
- Maven 3.8 o superior
- MySQL Server 8.0 o superior

### 1. Clonar el repositorio

```bash
git clone https://github.com/Dangelcrack/ShopCardApi.git
cd ShopCardApi
````

### 2. Crear la base de datos

```bash
mysql -u root -p -e "CREATE DATABASE shopcard;"
```

### 3. Configurar las credenciales

Edita el archivo:

```text
src/main/resources/application.properties
```

Añade tu configuración:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopcard
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

> ⚠️ **Seguridad:** Nunca subas tus credenciales reales a GitHub. Utiliza variables de entorno o añade el archivo correspondiente al `.gitignore`.

### 4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8080
```

---

## 🔗 API Reference

| Método | Endpoint              | Descripción                      |
| ------ | --------------------- | -------------------------------- |
| GET    | `/api/productos`      | Obtener el catálogo completo     |
| GET    | `/api/productos/{id}` | Obtener un producto por ID       |
| POST   | `/api/productos`      | Registrar una nueva carta        |
| PUT    | `/api/productos/{id}` | Actualizar un producto existente |
| DELETE | `/api/productos/{id}` | Eliminar un producto             |

---

## 🧪 Testing

Actualmente el proyecto está en proceso de implementación de pruebas automatizadas.

### Stack previsto

* ✅ JUnit 5
* ✅ Mockito

**Estado:** En desarrollo de cobertura de pruebas unitarias para la capa de servicios.

---

## 📁 Estructura del Proyecto

```text
src/
└── main/
    └── java/
        └── com/
            └── github/
                └── dangelcrack/
                    ├── controller/   # Exposición de endpoints REST
                    ├── model/        # Entidades JPA
                    ├── repository/   # Acceso a datos
                    └── service/      # Lógica de negocio
```

---

## 👤 Autor

**Ángel Guerrero**

* GitHub: **@Dangelcrack**
* Email: **[angelguerrero540@gmail.com](mailto:angelguerrero540@gmail.com)**

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia **MIT**.

```
```
