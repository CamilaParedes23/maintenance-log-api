# 🔧 MaintenanceLog API RESTful

Una API RESTful completa para la gestión de logs de mantenimiento, desarrollada con Spring Boot 4.0.0 y Java 17.

**Autor:** Paredes  
**Versión:** 1.0  
**Universidad:** ESPE  

## 📋 Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución](#ejecución)
- [API Endpoints](#api-endpoints)
- [Docker](#docker)
- [Pruebas](#pruebas)
- [Estructura del Proyecto](#estructura-del-proyecto)

## 📖 Descripción

Esta API permite gestionar logs de mantenimiento con operaciones CRUD completas, incluyendo búsquedas avanzadas por estado, técnico, fechas y título. Implementa principios REST, manejo de errores robusto y validaciones de datos.

## ✨ Características

- ✅ **CRUD Completo**: Crear, leer, actualizar y eliminar logs de mantenimiento
- ✅ **Búsquedas Avanzadas**: Filtrado por estado, técnico, fechas y título
- ✅ **Validaciones**: Validación robusta de datos de entrada
- ✅ **Manejo de Errores**: Respuestas de error estructuradas y descriptivas
- ✅ **Dockerización**: Contenedores Docker para aplicación y base de datos
- ✅ **Documentación**: API documentada con colección Postman completa
- ✅ **Logging**: Sistema de logging detallado para monitoreo
- ✅ **Arquitectura Limpia**: Separación en capas (Controller, Service, Repository, DTO)

## 🏗️ Arquitectura

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │───▶│    Service      │───▶│   Repository    │───▶│    Database     │
│     Layer       │    │     Layer       │    │     Layer       │    │    (MySQL)      │
└─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘
         ▲                       ▲                       ▲
         │                       │                       │
    ┌─────────┐             ┌─────────┐             ┌─────────┐
    │   DTO   │             │ Mapper  │             │ Entity  │
    └─────────┘             └─────────┘             └─────────┘
```

## 🛠️ Tecnologías

- **Java 17**: Lenguaje de programación
- **Spring Boot 4.0.0**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **Spring Web**: API REST
- **Spring Validation**: Validación de datos
- **MySQL 8.0**: Base de datos
- **Lombok**: Reducción de boilerplate code
- **Docker**: Contenedorización (standalone)
- **Gradle**: Gestión de dependencias y build

## ⚙️ Requisitos Previos

- Java 17 o superior
- Docker (sin docker-compose)
- MySQL 8.0 (si ejecutas sin Docker)
- Postman (para pruebas)
- Git

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone <repository-url>
cd Paredes_MaintenanceLog
```

### 2. Configuración de Base de Datos

La aplicación está configurada para conectarse a MySQL. Las credenciales por defecto son:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/maintenance_log_db
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Compilar el Proyecto

```bash
# Linux/Mac
./gradlew clean build

# Windows
gradlew.bat clean build
```

## 🏃 Ejecución

### Opción 1: Con Contenedores Docker Separados (Recomendado)

```bash
# Paso 1: Configurar MySQL con script automático
.\setup-mysql.bat    # Windows
./setup-mysql.sh     # Linux/Mac

# Paso 2: Construir imagen de la API
./build-and-push.sh   # Linux/Mac
# O manualmente: docker build -t paredes/maintenance-log-api:1.0 .

# Paso 3: Ejecutar API conectada a MySQL
docker run -p 8080:8080 --network maintenance-network \
    -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-maintenance:3306/maintenance_log_db \
    -e SPRING_DATASOURCE_USERNAME=root \
    -e SPRING_DATASOURCE_PASSWORD=root \
    paredes/maintenance-log-api:1.0
```

### Opción 2: Ejecución Local (Desarrollo)

```bash
# 1. Configurar MySQL local
CREATE DATABASE maintenance_log_db;

# 2. Ejecutar aplicación
./gradlew bootRun     # Linux/Mac
gradlew.bat bootRun   # Windows
```

### Opción 3: Docker Manual

```bash
# Configurar MySQL manualmente
docker network create maintenance-network
docker run -d --name mysql-db --network maintenance-network \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=maintenance_log_db \
  -p 3306:3306 mysql:8.0

# Construir y ejecutar API
docker build -t paredes/maintenance-log-api:1.0 .
docker run -p 8080:8080 --network maintenance-network \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/maintenance_log_db \
  paredes/maintenance-log-api:1.0
```

## 📊 API Endpoints

### Base URL
```
http://localhost:8080/api/v1/maintenance-logs
```

### Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/` | Crear nuevo log |
| `GET` | `/` | Obtener todos los logs |
| `GET` | `/{id}` | Obtener log por ID |
| `PUT` | `/{id}` | Actualizar log |
| `DELETE` | `/{id}` | Eliminar log |

### Endpoints de Búsqueda

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/status/{status}` | Buscar por estado |
| `GET` | `/technician/{technician}` | Buscar por técnico |
| `GET` | `/date-range?startDate={date}&endDate={date}` | Buscar por rango de fechas |
| `GET` | `/search?title={title}` | Buscar por título |

### Estados Válidos
- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`
- `CANCELLED`

### Ejemplo de Payload

```json
{
  \"title\": \"Mantenimiento preventivo servidor\",
  \"description\": \"Revisión general del servidor principal\",
  \"date\": \"2024-12-01\",
  \"technician\": \"Juan Pérez\",
  \"status\": \"PENDING\"
}
```

## 🐳 Docker

### Construcción de Imagen

```bash
# Usar script automatizado (Linux/Mac)
./build-and-push.sh

# Usar script automatizado (Windows)
build-and-push.bat

# Manual
docker build -t paredes/maintenance-log-api:1.0 .
```

### Publicar en Docker Hub

```bash
# Login en Docker Hub
docker login

# Push de imagen
### Contenedores Separados
docker push paredes/maintenance-log-api:latest
- **mysql-maintenance**: Base de datos MySQL 8.0 (puerto 3306)
- **paredes/maintenance-log-api**: API Spring Boot (puerto 8080)
### Servicios en Docker Compose

- **mysql-db**: Base de datos MySQL 8.0 (puerto 3307)
- **maintenance-api**: API Spring Boot (puerto 8080)

## 🧪 Pruebas

### Colección Postman

1. Importar `MaintenanceLog-API-Postman-Collection.json` en Postman
2. La colección incluye:
   - ✅ Operaciones CRUD completas
   - ✅ Pruebas de validación
   - ✅ Casos de error
   - ✅ Búsquedas y filtros
   - ✅ Tests automatizados

### Ejecutar Pruebas

```bash
# Ejecutar todas las pruebas
./gradlew test

# Ejecutar con cobertura
./gradlew test jacocoTestReport
```

### Health Check

```bash
# Verificar que la API esté ejecutándose
curl http://localhost:8080/api/v1/maintenance-logs

# Respuesta esperada: []
```

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/ec/edu/espe/paredes_maintenancelog/
│   │   ├── controller/          # Controladores REST
│   │   ├── service/            # Lógica de negocio
│   │   │   └── impl/
│   │   ├── repository/         # Acceso a datos
│   │   ├── entity/            # Entidades JPA
│   │   ├── dto/               # DTOs de transferencia
│   │   ├── mapper/            # Conversores DTO-Entity
│       └── application-docker.properties
│   │   └── ParedesMaintenanceLogApplication.java
├── setup-mysql.sh            # Script configuración MySQL (Linux/Mac)
├── build-and-push.sh          # Script construcción Docker (Linux/Mac)
├── Dockerfile                 # Imagen standalone de la aplicación
│       └── templates/
├── test/                      # Pruebas unitarias
├── docker-compose.yml         # Orquestación de contenedores
├── Dockerfile                 # Imagen de la aplicación
├── build.gradle              # Configuración Gradle
└── README.md                  # Documentación
```

## 📝 Ejemplos de Uso

### Crear un Log

```bash
curl -X POST http://localhost:8080/api/v1/maintenance-logs \\
  -H \"Content-Type: application/json\" \\
  -d '{
    \"title\": \"Actualización sistema\",
    \"description\": \"Actualización de seguridad del sistema operativo\",
    \"date\": \"2024-12-01\",
    \"technician\": \"María García\",
    \"status\": \"PENDING\"
  }'
```

### Obtener Todos los Logs

```bash
curl http://localhost:8080/api/v1/maintenance-logs
```

### Buscar por Estado

```bash
curl http://localhost:8080/api/v1/maintenance-logs/status/PENDING
```

## 🚨 Solución de Problemas

docker ps | grep mysql

```bash
docker logs mysql-maintenance
docker-compose ps

# Revisar logs de MySQL
docker-compose logs mysql-db
```

### Puerto en Uso

```bash
# Cambiar puerto en docker-compose.yml o application.properties
# Puerto por defecto: 8080
```

### Problemas de Build

```bash
# Limpiar y reconstruir
./gradlew clean build --refresh-dependencies
```

## 📈 Características Avanzadas

- **Transacciones**: Operaciones atómicas con `@Transactional`
- **Validación**: DTOs con Bean Validation
- **Paginación**: Preparado para implementar paginación con Spring Data
- **Auditoria**: Logging detallado de todas las operaciones
- **Cross-Origin**: CORS habilitado para desarrollo frontend

## 🔗 Enlaces Útiles

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Docker Hub](https://hub.docker.com/)
- [Postman Documentation](https://www.postman.com/api-documentation-tool/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

## 📄 Licencia

Este proyecto es desarrollado con fines académicos para la Universidad ESPE.

## 👨‍💻 Autor

**Paredes**  
Universidad: ESPE  
Materia: Sistemas Distribuidos  
Fecha: Diciembre 2024

---

¿Necesitas ayuda? Revisa la documentación o contacta al desarrollador.
