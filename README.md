# Sistema de Soporte Técnico basado en Microservicios

## Descripción del Proyecto

Este proyecto consiste en una aplicación desarrollada con arquitectura de microservicios para gestionar usuarios y tickets de soporte técnico.

El sistema permite registrar usuarios, consultar información de usuarios y crear tickets de soporte asociados a usuarios existentes.

La solución fue desarrollada utilizando Spring Boot, Spring Data JPA, Spring Security, OpenFeign y MySQL.

---

# Arquitectura del Sistema

El proyecto está compuesto por dos microservicios:

## UserService

Responsable de administrar los usuarios del sistema.

Funciones principales:

* Registrar usuarios.
* Consultar todos los usuarios.
* Buscar usuarios por ID.
* Validar información mediante DTO y Bean Validation.
* Gestionar la persistencia en base de datos MySQL.

Base de datos:

* support_users_db

Puerto:

* 8081

---

## TicketService

Responsable de administrar los tickets de soporte.

Funciones principales:

* Registrar tickets.
* Consultar tickets existentes.
* Verificar que el usuario exista antes de crear un ticket.
* Comunicarse con UserService mediante Feign Client.

Base de datos:

* support_tickets_db

Puerto:

* 8082

---

# Flujo de Funcionamiento

1. El cliente envía una solicitud para crear un ticket.
2. TicketService recibe la solicitud.
3. TicketService consulta a UserService utilizando Feign Client.
4. UserService verifica si el usuario existe.
5. Si el usuario existe, el ticket es almacenado en la base de datos.
6. Si el usuario no existe, se devuelve un mensaje de error.
7. Se responde al cliente con el resultado de la operación.

---

# Tecnologías Utilizadas

* Java 24
* Spring Boot 3.4.1
* Spring Data JPA
* Spring Security
* Spring Validation
* OpenFeign
* Flyway
* MySQL
* Gradle
* GitHub

---

# Seguridad

La aplicación utiliza Spring Security con autenticación HTTP Basic.

Credenciales configuradas:

Usuario:

admin

Contraseña:

admin123

Estas credenciales permiten acceder a los endpoints protegidos del sistema.

---

# Validaciones Implementadas

Usuarios:

* Nombre obligatorio.
* Correo obligatorio.
* Validación de formato de correo electrónico.

Tickets:

* Título obligatorio.
* Descripción obligatoria.
* Usuario asociado obligatorio.

---

# Manejo de Excepciones

Se implementó un manejo centralizado de excepciones mediante:

* @ControllerAdvice
* @RestControllerAdvice

Esto permite entregar respuestas claras y códigos HTTP adecuados cuando ocurre un error.

---

# Comunicación entre Microservicios

La comunicación se realiza mediante OpenFeign.

TicketService utiliza UserClient para consultar UserService antes de registrar un ticket.

De esta forma se garantiza que solamente se creen tickets asociados a usuarios válidos.

---

# Registro de Eventos (Logs)

Se implementó SLF4J para registrar eventos importantes del sistema:

* Creación de usuarios.
* Creación de tickets.
* Consultas realizadas.
* Errores y excepciones.
* Comunicación entre microservicios.

---

# Estructura del Proyecto

Cada microservicio utiliza el patrón CSR:

* Controller → Manejo de solicitudes HTTP.
* Service → Lógica de negocio.
* Repository → Acceso a base de datos.

Además se utilizan:

* DTOs
* Configuración de seguridad
* Configuración Feign
* Manejo global de excepciones

---

# Ejecución del Proyecto

1. Crear las bases de datos MySQL.
2. Configurar usuario y contraseña en application.properties.
3. Ejecutar UserService.
4. Ejecutar TicketService.
5. Probar los endpoints utilizando Postman.

---

# Conclusión

Este proyecto implementa una arquitectura de microservicios utilizando buenas prácticas de desarrollo backend, permitiendo la comunicación entre servicios, validación de datos, persistencia en base de datos, seguridad básica y manejo adecuado de errores.
