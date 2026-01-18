# Sales Data Automation Pipeline 🚀

Este proyecto es una solución integral para la automatización de ingesta de datos de ventas. Permite procesar archivos Excel complejos, gestionar un catálogo de productos y evitar la duplicidad de registros mediante una arquitectura de limpia.

## 🛠️ Tecnologías Utilizadas
* **Backend:** Java 21, Spring Boot 3, Spring Data JPA.
* **Base de Datos:** PostgreSQL corriendo en **Docker**.
* **Procesamiento de Archivos:** Apache POI.
* **BI:** Power BI Desktop para la visualización de datos.
* **Pruebas:** Postman.

## 🌟 Características Principales
* **Idempotencia:** Control de duplicados mediante `invoice_number` único.
* **Auto-catálogo:** Creación automática de productos nuevos al detectar SKUs no registrados.
* **Resiliencia:** Manejo robusto de errores de formato en celdas de Excel (fechas, números y textos).
* **Dockerizado:** Entorno de base de datos listo para usar con un solo comando.

## 🚀 Cómo empezar

### Requisitos
* Docker y Docker Compose.
* JDK 17 o superior.
* Maven.

### Instalación
1. Clona el repositorio:
   ```bash
   git clone [https://github.com/tu-usuario/sales-automation-pipeline.git](https://github.com/tu-usuario/sales-automation-pipeline.git)

2. **Levantar la base de datos:**
   ```bash
   docker-compose up -d