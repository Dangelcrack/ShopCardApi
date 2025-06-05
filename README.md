# 🛒 ShopCard Backend - La Máquina de las Cartas ♠️

El backend más molón para gestionar tu colección de cartas como un auténtico duque del mazo.

## 🚀 Pa' empezar

### 📋 Requisitos
- Java 17+
- Maven 3.8+
- MySQL 8+

### ⚙️ Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/shopcard.git
   cd shopcard
   ```

2. Crea la base de datos:
   ```sql
   CREATE DATABASE shopcard;
   ```

3. Configura `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/shopcard
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

4. Compila:
   ```bash
   mvn clean install
   ```

## 🏃‍♂️ Ejecución

```bash
mvn spring-boot:run
```

## 🎯 Características

- Gestión completa de cartas
- Búsquedas con filtros avanzados
- Sistema de valoraciones y rarezas
- API RESTful documentada

## 🧑‍💻 Endpoints principales

| Método | Ruta               | Descripción         |
|--------|--------------------|---------------------|
| GET    | /api/productos        | Obtener todas las cartas |
| POST   | /api/productos       | Crear nueva carta |
| GET    | /api/productos/{id}   | Obtener carta por ID |
| PUT    | /api/productos/{id}   | Actualizar carta |
| DELETE | /api/productos/{id}   | Eliminar carta |

## 🆘 Soporte

Si algo falla:

- Verifica la conexión a la base de datos
- Revisa los logs de la aplicación
- Abre un issue en GitHub

## 📜 Licencia

MIT - Haz lo que quieras pero no me culpes si algo explota.

