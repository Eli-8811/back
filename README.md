# 🚀 Backend Core Project - Spring Boot

Este proyecto es una implementación de referencia de una API REST robusta utilizando **Java 21+** y **Spring Boot**, diseñada para demostrar el dominio de patrones de arquitectura, programación funcional y buenas prácticas de desarrollo.

## 🛠️ Tecnologías Principales
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA / Hibernate
* **Validación:** Jakarta Validation (Bean Validation)
* **Utilidades:** Lombok, Logback (SLF4J)
* **Base de Datos:** H2 / PostgreSQL (Configurable)

---

## 🏗️ Arquitectura y Patrones de Diseño
El proyecto sigue una arquitectura de capas (Controller - Service - Repository) e implementa los siguientes patrones:

* **Inyección de Dependencias:** Gestión de desacoplamiento mediante el contenedor de Spring.
* **Singleton:** Definición de Beans de servicio y repositorios con una única instancia.
* **Data Transfer Object (DTO):** Uso de `UserDTO` y `GenericDTO` para desacoplar la capa de persistencia de la capa de presentación.
* **Builder Pattern:** Implementado mediante Lombok en DTOs y Entidades para una creación de objetos fluida.
* **Repository Pattern:** Abstracción completa de la lógica de acceso a datos.

---

## 💻 Conceptos de Programación Aplicados

### 🧩 Programación Orientada a Objetos (POO)
* **Herencia y Clases Abstractas:** Uso de `BaseEntity` (Abstract) para centralizar metadatos como fechas de creación y estado.
* **Encapsulamiento:** Atributos privados con acceso controlado mediante anotaciones de Lombok.
* **Polimorfismo:** Implementación dinámica de interfaces en `UserRepository`.

### ⚡ Programación Funcional & Java 8+
Se aprovecha la potencia de Java moderno para escribir código más declarativo y limpio:
* **Stream API:** Procesamiento eficiente de colecciones en `UserService`.
* **Optional:** Manejo seguro de nulidad para evitar el clásico `NullPointerException`.
* **Expresiones Lambda & Method References:** Uso de `this::toDto` y predicados para transformar datos.

### 🛡️ Manejo de Errores y Excepciones
Estrategia integral de manejo de excepciones mediante un **Global Exception Handler** (`@RestControllerAdvice`):
* **Checked Exceptions:** Uso de `throws Exception` en flujos críticos de infraestructura.
* **Unchecked Exceptions:** Implementación de `BusinessException` (RuntimeException) para validaciones de lógica de negocio.
* **Bloques Try-Catch-Finally:** Garantizan la consistencia de los logs y la liberación de procesos en la capa de servicio.

---

## 📦 Estructura del Proyecto
```text
src/main/java/com/core/back/
├── controller/    # Puntos de entrada de la API (Endpoints)
├── service/       # Lógica de negocio y transformación
├── repository/    # Interfases de acceso a datos
├── entity/        # Modelos de base de datos (JPA)
├── dto/           # Objetos de transferencia y Generics
└── exception/     # Manejo global y excepciones personalizadas