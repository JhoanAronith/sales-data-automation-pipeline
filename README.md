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

## 📊 Estructura del Excel Requerida
Para que el procesamiento sea exitoso, el archivo `.xlsx` debe seguir estrictamente este orden de columnas (empezando desde la columna A):

| Índice | Columna | Descripción | Ejemplo |
| :--- | :--- | :--- | :--- |
| 0 | **Factura** | ID único de transacción | FAC-001 |
| 1 | **Fecha** | Fecha de venta (Formato Fecha) | 15/01/2026 |
| 2 | **SKU** | Código único del producto | LAP-001 |
| 3 | **Producto** | Nombre descriptivo | Laptop Pro |
| 4 | **Categoría** | Categoría del producto | Electrónica |
| 5 | **Cantidad** | Unidades vendidas (Entero) | 5 |
| 6 | **Precio Unit.** | Precio por unidad (Decimal) | 1200.50 |
| 7 | **Región** | Ubicación de la venta | Norte |


## 🛠️ Uso de la API
Una vez iniciada la aplicación, puedes cargar datos utilizando Postman o cURL:

* **Endpoint:** `http://localhost:8080/api/excel/import`
* **Método:** `POST`
* **Cuerpo:** `form-data`
* **Key:** `file` (Tipo: File)

## 🗄️ Base de Datos
El proyecto utiliza Hibernate para generar automáticamente las tablas. Sin embargo, el esquema principal se basa en:

```sql
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    sku VARCHAR(50) UNIQUE,
    name VARCHAR(255),
    category VARCHAR(100)
);

CREATE TABLE sales_data (
    id SERIAL PRIMARY KEY,
    invoice_number VARCHAR(50) UNIQUE,
    sale_date DATE,
    quantity INTEGER,
    unit_price DECIMAL,
    region VARCHAR(100),
    product_id INTEGER REFERENCES products(id)
);
