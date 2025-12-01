# 🚀 Guía de Despliegue - MaintenanceLog API

## 📋 Resumen del Proyecto

**API RESTful MaintenanceLog** - Una aplicación completa para gestión de logs de mantenimiento desarrollada con Spring Boot 4.0.0 y Java 17.

### ✅ Estado del Proyecto

- ✅ **Código Fuente Completo**: Implementación terminada
- ✅ **Arquitectura**: Controller + Service + Repository + DTO + Entity
- ✅ **Base de Datos**: Configuración MySQL lista
- ✅ **Docker**: Dockerfile standalone creado (sin docker-compose)
- ✅ **Documentación**: README.md y colección Postman completos
- ✅ **Validaciones**: Bean Validation implementado
- ✅ **Manejo de Errores**: GlobalExceptionHandler funcional

## 🏗️ Componentes Implementados

### Entidades y Modelos
- `MaintenanceLog.java` - Entidad principal con JPA
- `MaintenanceStatus` enum - Estados del mantenimiento
- DTOs: Create, Update, Response

### Capas de Servicio
- `MaintenanceLogService` - Interface de servicio
- `MaintenanceLogServiceImpl` - Implementación completa
- `MaintenanceLogRepository` - Repository con JPA

### API REST
- `MaintenanceLogController` - 9 endpoints REST
- Operaciones CRUD completas
- Búsquedas avanzadas (por estado, técnico, fecha, título)
- Validación de entrada

### Docker y Despliegue
- `Dockerfile` - Imagen standalone de la aplicación
- `setup-mysql.sh` - Script para configurar MySQL (Linux/Mac)
- Scripts de construcción para Linux/Mac

## 🚦 Opciones de Ejecución

### 1. Ejecución con Docker Compose (Recomendado)

```bash
# Clonar y navegar al directorio
cd Paredes_MaintenanceLog

# Ejecutar aplicación completa
docker-compose up -d

# Verificar estado
docker-compose ps
docker-compose logs -f maintenance-api
```

**Servicios incluidos:**
- MySQL 8.0 (puerto 3307)
- API Spring Boot (puerto 8080)
- Red privada Docker
- Volúmenes persistentes

### 2. Construcción Manual de Imagen

```bash
# Construcción
docker build -t paredes/maintenance-log-api:1.0 .

# Ejecución (requiere MySQL externo)
docker run -p 8080:8080 \\
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/maintenance_log_db \\
  -e SPRING_DATASOURCE_USERNAME=root \\
  -e SPRING_DATASOURCE_PASSWORD=root \\
  paredes/maintenance-log-api:1.0
```

### 3. Ejecución Local (Desarrollo)

```bash
# Requisitos: MySQL local en puerto 3306
# Base de datos: maintenance_log_db

# Compilar y ejecutar
./gradlew bootRun
```

## 🔧 Configuración de Base de Datos

### MySQL Local
```sql
CREATE DATABASE maintenance_log_db;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin123';
GRANT ALL PRIVILEGES ON maintenance_log_db.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

### Variables de Entorno
```bash
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/maintenance_log_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```

## 📊 Endpoints de la API

### URL Base: `http://localhost:8080/api/v1/maintenance-logs`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/` | Crear log |
| `GET` | `/` | Listar todos |
| `GET` | `/{id}` | Obtener por ID |
| `PUT` | `/{id}` | Actualizar |
| `DELETE` | `/{id}` | Eliminar |
| `GET` | `/status/{status}` | Buscar por estado |
| `GET` | `/technician/{name}` | Buscar por técnico |
| `GET` | `/date-range?start&end` | Buscar por fechas |
| `GET` | `/search?title` | Buscar por título |

### Ejemplo de Payload
```json
{
  "title": "Mantenimiento preventivo servidor",
  "description": "Revisión general del servidor principal",
  "date": "2024-12-01",
  "technician": "Juan Pérez",
  "status": "PENDING"
}
```

## 🧪 Pruebas con Postman

1. **Importar Colección**: `MaintenanceLog-API-Postman-Collection.json`
2. **Configurar Variable**: `baseUrl = http://localhost:8080`
3. **Ejecutar Pruebas**: 
   - Operaciones CRUD
   - Validaciones
   - Casos de error
   - Búsquedas

## 🔍 Verificación del Despliegue

### Health Check
```bash
# Verificar API
curl http://localhost:8080/api/v1/maintenance-logs

# Respuesta esperada: [] (array vacío)
```

### Crear Primer Log
```bash
curl -X POST http://localhost:8080/api/v1/maintenance-logs \\
  -H "Content-Type: application/json" \\
  -d '{
    "title": "Test inicial",
    "description": "Prueba de funcionamiento",
    "date": "2024-12-01",
    "technician": "Admin",
    "status": "PENDING"
  }'
```

## 📈 Características Implementadas

### Arquitectura
- ✅ Separación en capas clara
- ✅ DTOs para transferencia de datos
- ✅ Mappers para conversión
- ✅ Repository pattern con JPA

### Validaciones
- ✅ Bean Validation en DTOs
- ✅ Validación de campos obligatorios
- ✅ Enum para estados válidos
- ✅ Manejo de errores robusto

### Base de Datos
- ✅ JPA/Hibernate ORM
- ✅ MySQL connector
- ✅ DDL automático (hibernate.ddl-auto=update)
- ✅ Transacciones

### API REST
- ✅ Endpoints RESTful estándar
- ✅ HTTP status codes correctos
- ✅ JSON request/response
- ✅ CORS habilitado

## 🚨 Resolución de Problemas

### Error de Conexión MySQL
```bash
# Verificar estado de contenedores individuales
docker ps -a | grep mysql
docker ps -a | grep maintenance

# Revisar logs de MySQL
docker logs mysql-maintenance

# Revisar logs de la API
docker logs <container-name>

# Reiniciar contenedor MySQL
docker restart mysql-maintenance

# Verificar conectividad de red
docker network ls
docker network inspect maintenance-network
```

### Puerto en Uso
```bash
# Verificar puertos ocupados
netstat -an | findstr :8080
netstat -an | findstr :3306

# Para cambiar puerto de la API, usar variable de entorno:
# -e SERVER_PORT=8081

# Para MySQL en otro puerto:
# -p 3307:3306 (host:contenedor)
```

### Error de Build Docker
```bash
# Limpiar construcciones previas
docker system prune -f

# Reconstruir imagen desde cero
docker build --no-cache -t paredes/maintenance-log-api:1.0 .
```

## 📋 Checklist de Despliegue

- [ ] Java 17+ instalado
- [ ] Docker disponible
- [ ] Puertos 8080 y 3306 libres
- [ ] Proyecto clonado/descargado
- [ ] Configurar MySQL (`setup-mysql.bat/.sh` o manual)
- [ ] Construir imagen (`build-and-push.sh` o manual)
- [ ] Ejecutar contenedor de la API
- [ ] Verificar con `curl http://localhost:8080/api/v1/maintenance-logs`
- [ ] Importar colección Postman
- [ ] Ejecutar pruebas básicas

## 🎯 Próximos Pasos

1. **Desplegar en Producción**: Configurar variables de entorno
2. **Monitoring**: Agregar métricas y logging
3. **Seguridad**: Implementar autenticación/autorización  
4. **Documentación**: Swagger/OpenAPI
5. **CI/CD**: Pipeline de despliegue automático

---

**¡La API MaintenanceLog está lista para producción!** 🚀

Desarrollado por: **Paredes**  
Universidad: **ESPE**  
Fecha: **Diciembre 2024**
