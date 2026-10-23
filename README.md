# 🎬 Gestor de Películas · API REST

API REST desarrollada con **Spring Boot** para gestionar un catálogo de películas, sus directores y sus actores. Incluye validación de datos, gestión de errores con `ProblemDetail` y documentación con **OpenAPI 3.0 / Swagger**.

## 📑 Índice

- [Tecnologías](#-tecnologías)
- [Modelo de datos](#-modelo-de-datos)
- [Endpoints](#-endpoints)
- [Validaciones](#-validaciones)
- [Gestión de errores](#-gestión-de-errores)
- [Documentación OpenAPI y Swagger](#-documentación-openapi-y-swagger)
- [Datos de ejemplo](#-datos-de-ejemplo)
- [Cómo ejecutar el proyecto](#-cómo-ejecutar-el-proyecto)
- [Estructura del proyecto](#-estructura-del-proyecto)

## 🛠 Tecnologías

- Java
- Spring Boot (Spring Web, Spring Data JPA, Validation)
- Lombok
- springdoc-openapi (OpenAPI 3.0 + Swagger UI)
- Maven
- IntelliJ IDEA

## 🗂 Modelo de datos

El proyecto gestiona tres entidades:

| Entidad | Atributos | Relaciones |
|---------|-----------|------------|
| **Director** | `id`, `name`, `anioNacimiento` | Un director dirige muchas películas (1:N) |
| **Película** | `id`, `titulo` (único), `genero`, `fechaEstreno` | Pertenece a un director; tiene muchos actores (M:N) |
| **Actor** | `id`, `name` | Participa en muchas películas (M:N) |

La relación entre **Película** y **Actor** es muchos a muchos y se guarda en la tabla intermedia `reparto`.

```
Director 1 ────── N Película N ────── M Actor
                         (tabla reparto)
```

Para transferir datos se usan **DTOs anidados** implementados como `record`, con métodos estáticos de conversión (`of` / `to`).

## 🔌 Endpoints

> Revisa las rutas exactas en Swagger UI, que siempre refleja el estado actual de la API.

### Directores

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/v1/directores` | Listar todos los directores |
| `GET` | `/api/v1/directores/{id}` | Obtener un director |
| `POST` | `/api/v1/directores` | Crear un director |
| `PUT` | `/api/v1/directores/{id}` | Editar un director |
| `DELETE` | `/api/v1/directores/{id}` | Eliminar un director |

### Películas

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/v1/peliculas` | Listar todas las películas |
| `GET` | `/api/v1/peliculas/{peliculaId}` | Obtener una película con su reparto |
| `POST` | `/api/v1/peliculas` | Crear una película (requiere el id de un director existente) |
| `PUT` | `/api/v1/peliculas/{peliculaId}` | Editar una película |
| `DELETE` | `/api/v1/peliculas/{peliculaId}` | Eliminar una película |
| `POST` | `/api/v1/peliculas/{peliculaId}/actores/{actorId}` | Añadir un actor al reparto de una película |

### Actores

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/actor` | Listar todos los actores |
| `GET` | `/api/actor/{id}` | Obtener un actor |
| `POST` | `/api/actor` | Crear un actor |
| `PUT` | `/api/actor/{id}` | Editar un actor |
| `DELETE` | `/api/actor/{id}` | Eliminar un actor |

## ✅ Validaciones

- Anotaciones de Bean Validation en los DTOs de entrada (`@NotBlank`, `@NotNull`, etc.).
- **Validación personalizada:** un director debe tener al menos **18 años**.
- El **título** de una película debe ser único.

## ⚠️ Gestión de errores

Los errores se devuelven con el formato estándar **`ProblemDetail`** (RFC 7807):

| Código | Situación |
|--------|-----------|
| `400 Bad Request` | Datos de entrada no válidos |
| `404 Not Found` | Director, película o actor inexistente |
| `409 Conflict` | `PeliculaYaExisteException`: ya existe una película con ese título |
| `409 Conflict` | `ActorYaEnRepartoException`: el actor ya está en el reparto de esa película |

## 📖 Documentación OpenAPI y Swagger

La documentación se genera automáticamente con **springdoc-openapi**. Con la aplicación en marcha:

| Recurso | URL |
|---------|-----|
| Swagger UI | http://localhost:8080/swagger-ui/index.html |
| Especificación OpenAPI (JSON) | http://localhost:8080/v3/api-docs |
| Especificación OpenAPI (YAML) | http://localhost:8080/v3/api-docs.yaml |

Los controladores están anotados con `@Operation` y `@ApiResponses` para documentar los códigos de respuesta y de error de cada endpoint.

## 🌱 Datos de ejemplo

Al arrancar, la clase `DataSeed` (paquete `utils`) carga datos de prueba:

- 10 directores
- 40 actores
- 30 películas con su reparto

## 🚀 Cómo ejecutar el proyecto

### Requisitos

- JDK instalado (la versión indicada en el `pom.xml`)
- Maven (o el wrapper `mvnw` incluido)

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/gilgumar25-creator/ProyectoAPIRest-GestorPeliculas.git
cd ProyectoAPIRest-GestorPeliculas

# 2. Arrancar la aplicación
./mvnw spring-boot:run
```

En Windows: `mvnw.cmd spring-boot:run`. También puedes ejecutar la clase principal desde IntelliJ IDEA.

La API quedará disponible en `http://localhost:8080`.

## 📁 Estructura del proyecto

```
com.salesianos.dam.GestordePeliculas
├── controller     # Controladores REST
├── dto            # DTOs (records)
├── error          # Excepciones y manejo de errores (ProblemDetail)
├── model          # Entidades JPA
├── repo           # Repositorios Spring Data
├── service        # Lógica de negocio
├── validation     # Validaciones personalizadas
└── utils          # DataSeed
```

## 👤 Autor

Proyecto realizado como parte de la formación en DAM.