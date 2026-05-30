# 🛠️ Sistema de Gestión de Tickets (Microservicios)

## 📌 1. El Desafío del Negocio (Problemática)
Anteriormente, la administración de incidentes de soporte técnico se gestionaba de forma **manual**, lo que generaba ineficiencias críticas para la organización:
* **Fallas de Registro:** Se creaban tickets asociados a usuarios inexistentes debido a la falta de validación inmediata.
* **Fuga de Información:** Las solicitudes se extraviaban entre planillas de cálculo y correos electrónicos sin seguimiento.
* **Falta de Trazabilidad:** No existía visibilidad real sobre los tiempos de resolución ni asignación clara de responsabilidades.

**La Solución:** Automatizar el flujo completo mediante una arquitectura desacoplada que valida la integridad de los datos en tiempo real y centraliza los reportes de manera segura.

---

## 🏗️ 2. Arquitectura de la Solución
El ecosistema se diseñó utilizando dos microservicios independientes que garantizan la tolerancia a fallos:

* **User Service (Puerto 8081):** Repositorio centralizado de los colaboradores y clientes autorizados en la plataforma. *(Base de datos: `support_users_db`)*.
* **Ticket Service (Puerto 8082):** Módulo encargado exclusivamente de la apertura y control de incidentes. *(Base de datos: `support_tickets_db`)*.

### 🔗 Integración Síncrona via OpenFeign
Para erradicar los registros erróneos, implementamos **Spring Cloud OpenFeign**. Al procesar un ticket, el sistema consulta automáticamente al servicio de usuarios:
* Si el ID de usuario es **válido**, el incidente se almacena de inmediato.
* Si el ID **no existe**, el sistema intercepta el flujo, bloquea la acción y emite una excepción controlada.

---

## 🧪 3. Guía de Validación (Pruebas en Postman)

> 🔐 **Capa de Seguridad:** Las rutas están restringidas mediante **Spring Security**. En cada solicitud se debe configurar **Basic Auth** con las credenciales jerárquicas: **Usuario:** `admin` / **Contraseña:** `admin123`.

### Paso 1: Alta de Usuario
* **Endpoint:** `POST` | `http://localhost:8081/api/users`
* **Carga Útil (JSON):**
```json
{
    "name": "Nayarett Rodríguez",
    "email": "nayar@supportsystem.com"
}
