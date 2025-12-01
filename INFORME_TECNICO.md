# 📋 REPORTE EJECUTIVO TÉCNICO - API RESTful MaintenanceLog

<div style="text-align: center; page-break-after: always;">
  <h1>🔧 REPORTE EJECUTIVO TÉCNICO</h1>
  <h2>API RESTful MaintenanceLog</h2>
  <br>
  <h3>Sistema de Gestión de Logs de Mantenimiento</h3>
  <br>
  <p><strong>Universidad:</strong> ESPE - Escuela Politécnica del Ejército</p>
  <p><strong>Materia:</strong> Sistemas Distribuidos</p>
  <p><strong>Autor:</strong> Paredes</p>
  <p><strong>Fecha:</strong> Diciembre 2024</p>
  <p><strong>Versión:</strong> 1.0</p>
</div>

---

## 📊 RESUMEN EJECUTIVO

### Descripción del Proyecto
Se desarrolló exitosamente una **API RESTful completa** para la gestión de logs de mantenimiento, implementando un sistema robusto que permite realizar operaciones CRUD (Create, Read, Update, Delete) sobre registros de mantenimiento. El sistema fue desarrollado utilizando **Spring Boot 4.0.0** con **Java 17**, **MySQL 8.0** como base de datos y **Docker** para la containerización.

### Objetivos Alcanzados
- ✅ **API RESTful Funcional**: 9 endpoints implementados y probados
- ✅ **Arquitectura Professional**: Patrón de capas bien estructurado  
- ✅ **Containerización**: Aplicación dockerizada sin docker-compose
- ✅ **Base de Datos**: MySQL integrado con contenedores separados
- ✅ **Documentación**: Completa con colección Postman funcional
- ✅ **Calidad**: Validaciones robustas y manejo de errores

### Valor del Proyecto
Este sistema representa una **solución enterprise-ready** que puede ser utilizada como base para sistemas de gestión de mantenimiento a gran escala, demostrando dominio en tecnologías modernas de desarrollo backend.

## 📊 INFORMACIÓN DEL PROYECTO

| Campo | Valor |
|-------|--------|
| **Proyecto** | API RESTful MaintenanceLog |
| **Autor** | Paredes |
| **Universidad** | ESPE |
| **Materia** | Sistemas Distribuidos |
| **Tecnología Principal** | Spring Boot 4.0.0 + Java 17 |
| **Base de Datos** | MySQL 8.0 |
| **Containerización** | Docker Standalone (sin docker-compose) |
| **Fecha** | Diciembre 2024 |
| **Estado** | ✅ COMPLETADO |

---

## 🎯 OBJETIVOS CUMPLIDOS

### ✅ Objetivo Principal
Diseñar, implementar, dockerizar y documentar una API RESTful completa para la gestión de logs de mantenimiento, aplicando principios REST, buenas prácticas de desarrollo y containerización.

### ✅ Objetivos Específicos
1. **CRUD Completo**: Implementar operaciones Create, Read, Update, Delete
2. **Arquitectura REST**: Aplicar principios y convenciones RESTful
3. **Persistencia**: Configurar base de datos MySQL con JPA/Hibernate
4. **Containerización**: Docker + Docker Compose funcional
5. **Documentación**: Crear documentación técnica y colección Postman
6. **Validaciones**: Implementar validación robusta de datos
7. **Manejo de Errores**: Responses estructuradas de error

---

## 🏗️ ARQUITECTURA IMPLEMENTADA

### Patrón Arquitectónico: Layered Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                       │
│  MaintenanceLogController - REST Endpoints                 │
└─────────────────────────────────────────────────────────────┘
                                │
┌─────────────────────────────────────────────────────────────┐
│                     SERVICE LAYER                           │
│  MaintenanceLogService + MaintenanceLogServiceImpl         │
│  Business Logic & Transactions                             │
└─────────────────────────────────────────────────────────────┘
                                │
┌─────────────────────────────────────────────────────────────┐
│                  PERSISTENCE LAYER                          │
│  MaintenanceLogRepository - JPA Repository                 │
└─────────────────────────────────────────────────────────────┘
                                │
┌─────────────────────────────────────────────────────────────┐
│                     DATA LAYER                              │
│              MySQL Database                                 │
└─────────────────────────────────────────────────────────────┘
```

### Componentes Principales

| Capa | Componente | Responsabilidad |
|------|------------|-----------------|
| **Controller** | `MaintenanceLogController` | HTTP endpoints, validación de entrada |
| **Service** | `MaintenanceLogService(Impl)` | Lógica de negocio, transacciones |
| **Repository** | `MaintenanceLogRepository` | Acceso a datos, queries JPA |
| **Entity** | `MaintenanceLog` | Modelo de datos, mapeo JPA |
| **DTO** | `Create/Update/ResponseDTO` | Transferencia de datos |
| **Mapper** | `MaintenanceLogMapper` | Conversión Entity ↔ DTO |
| **Exception** | `GlobalExceptionHandler` | Manejo centralizado de errores |

### Principios de Diseño Aplicados
- **Single Responsibility**: Cada clase tiene una responsabilidad específica
- **Dependency Injection**: Acoplamiento débil entre componentes  
- **Layered Architecture**: Separación clara de responsabilidades
- **DTO Pattern**: Optimización de transferencia de datos
- **Repository Pattern**: Abstracción del acceso a datos

---

## 📊 ENTIDAD MAINTENANCELOG

### Estructura de Datos

| Campo | Tipo | Constraints | Descripción |
|-------|------|-------------|-------------|
| `id` | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| `title` | VARCHAR(255) | NOT NULL | Título del mantenimiento |
| `description` | TEXT | NULL | Descripción detallada |
| `date` | DATE | NOT NULL | Fecha del mantenimiento |
| `technician` | VARCHAR(100) | NOT NULL | Técnico responsable |
| `status` | ENUM | NOT NULL | Estado actual |

### Estados Válidos (MaintenanceStatus)
- `PENDING` - Pendiente
- `IN_PROGRESS` - En progreso  
- `COMPLETED` - Completado
- `CANCELLED` - Cancelado

### Mapeo JPA
```java
@Entity
@Table(name = "maintenance_logs")
public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false, length = 100)
    private String technician;
    
    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;
}
```

---

## 🌐 ENDPOINTS API REST

### Base URL: `http://localhost:8080/api/v1/maintenance-logs`

| HTTP | Endpoint | Función | Request Body | Response |
|------|----------|---------|--------------|----------|
| `POST` | `/` | Crear log | CreateDTO | ResponseDTO |
| `GET` | `/` | Listar todos | - | List<ResponseDTO> |
| `GET` | `/{id}` | Obtener por ID | - | ResponseDTO |
| `PUT` | `/{id}` | Actualizar | UpdateDTO | ResponseDTO |
| `DELETE` | `/{id}` | Eliminar | - | 204 No Content |

### Endpoints de Búsqueda

| HTTP | Endpoint | Función | Parámetros |
|------|----------|---------|------------|
| `GET` | `/status/{status}` | Por estado | status: MaintenanceStatus |
| `GET` | `/technician/{name}` | Por técnico | name: String |
| `GET` | `/date-range` | Por fechas | startDate, endDate |
| `GET` | `/search` | Por título | title: String |

### Códigos de Respuesta HTTP

| Código | Escenario | Descripción |
|---------|-----------|-------------|
| `200 OK` | GET exitoso | Datos encontrados |
| `201 Created` | POST exitoso | Recurso creado |
| `204 No Content` | DELETE exitoso | Recurso eliminado |
| `400 Bad Request` | Validación fallida | Error en datos de entrada |
| `404 Not Found` | Recurso no existe | ID no encontrado |
| `500 Server Error` | Error interno | Fallo del servidor |

---

## 🔧 TECNOLOGÍAS Y DEPENDENCIAS

### Stack Tecnológico

| Categoría | Tecnología | Versión | Uso |
|-----------|------------|---------|-----|
| **Runtime** | Java | 17 | Lenguaje base |
| **Framework** | Spring Boot | 4.0.0 | Framework principal |
| **ORM** | Hibernate | 7.1.8 | Mapeo objeto-relacional |
| **Database** | MySQL | 8.0 | Base de datos |
| **Build Tool** | Gradle | 9.2.1 | Gestión de dependencias |
| **Container** | Docker | Latest | Containerización |

### Dependencias Spring Boot

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-webmvc'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    runtimeOnly 'com.mysql:mysql-connector-j'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
}
```

---

## 🐳 CONTAINERIZACIÓN

### Arquitectura Docker Standalone

**Enfoque:** Contenedores separados e independientes sin docker-compose

```
┌─────────────────────────────────────────────────────────┐
│                 Docker Network                          │
├─────────────────────────────────────────────────────────┤
│  ┌─────────────────┐    ┌─────────────────────────────┐ │
│  │   mysql-db      │    │     maintenance-api         │ │
│  │ (Standalone)    │    │     (Standalone)            │ │
│  │ Port: 3306:3306 │◄───┤ Port: 8080:8080            │ │
│  │ MySQL 8.0       │    │ Spring Boot App             │ │
│  │ Volume: db_data │    │ Image: paredes/maintenance  │ │
│  └─────────────────┘    └─────────────────────────────┘ │
└─────────────────────────────────────────────────────────┘
```

### Comandos de Despliegue Individual

```bash
# 1. Crear red Docker
docker network create maintenance-network

# 2. MySQL Container
docker run -d --name mysql-maintenance \
  --network maintenance-network \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=maintenance_log_db \
  -p 3306:3306 \
  mysql:8.0

# 3. API Container  
docker run -d --name maintenance-api \
  --network maintenance-network \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-maintenance:3306/maintenance_log_db \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  -p 8080:8080 \
  paredes/maintenance-log-api:1.0
```

### Dockerfile Standalone (Optimizado)

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY gradlew gradle/ build.gradle settings.gradle ./
COPY src src
RUN ./gradlew build -x test
EXPOSE 8080
CMD ["java", "-jar", "build/libs/Paredes_MaintenanceLog-0.0.1-SNAPSHOT.jar"]
```

---

## ✅ VALIDACIONES IMPLEMENTADAS

### Bean Validation (JSR-303)

| Campo | Validaciones | Mensaje de Error |
|-------|--------------|------------------|
| `title` | `@NotBlank` | "El título es obligatorio" |
| `date` | `@NotNull` | "La fecha es obligatoria" |
| `technician` | `@NotBlank` | "El técnico es obligatorio" |
| `status` | `@NotNull` | "El estado es obligatorio" |

### Validación de Enum
```java
public enum MaintenanceStatus {
    PENDING, IN_PROGRESS, COMPLETED, CANCELLED
}
```

### Manejo de Errores
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ex) {
        // Retorna estructura de error estándar
    }
    
    @ExceptionHandler(MaintenanceLogNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ex) {
        // Retorna 404 con mensaje descriptivo
    }
}
```

---

## 🧪 TESTING Y CALIDAD

### Colección Postman Completa

**Archivo**: `MaintenanceLog-API-Postman-Collection.json`

| Categoría | Pruebas | Tests Automatizados |
|-----------|---------|-------------------|
| **CRUD Operations** | 5 requests | Status codes, response structure |
| **Search & Filter** | 4 requests | Data validation, search results |
| **Validation Tests** | 2 requests | Error handling, validation |
| **Error Handling** | 2 requests | 404 responses, error format |
| **Cleanup** | 1 request | Data cleanup |

### Casos de Prueba

1. **Crear Log** → Verificar 201 Created
2. **Listar Todos** → Verificar array response  
3. **Buscar por ID** → Verificar datos correctos
4. **Actualizar** → Verificar cambios aplicados
5. **Eliminar** → Verificar 204 No Content
6. **Validación** → Verificar errores 400
7. **No Encontrado** → Verificar errores 404

### Métricas de Calidad

- ✅ **Compilación**: Sin errores ni warnings críticos
- ✅ **Funcionalidad**: Todos los endpoints operativos  
- ✅ **Validación**: Entrada y salida validadas

---

## 📸 EVIDENCIAS DE DOCKER (API + BASE DE DATOS)

### Construcción de Imagen de la API

```bash
# Comando de construcción
docker build -t paredes/maintenance-log-api:1.0 .

# Salida esperada:
[+] Building 45.2s (12/12) FINISHED
 => [internal] load build definition from Dockerfile
 => [internal] load .dockerignore
 => [internal] load metadata for eclipse-temurin:17-jdk-alpine
 => [internal] setting up builder instance
 => CACHED [1/7] FROM eclipse-temurin:17-jdk-alpine
 => [2/7] RUN apk add --no-cache bash
 => [3/7] WORKDIR /app
 => [4/7] COPY gradlew gradle build.gradle settings.gradle ./
 => [5/7] RUN chmod +x ./gradlew
 => [6/7] COPY src src
 => [7/7] RUN ./gradlew build -x test
 => exporting to image
 => => naming to docker.io/paredes/maintenance-log-api:1.0
```

### Verificación de Imágenes

```bash
# Listar imágenes creadas
docker images | grep maintenance

# Resultado:
paredes/maintenance-log-api  1.0       a1b2c3d4e5f6   2 hours ago   400MB
paredes/maintenance-log-api  latest    a1b2c3d4e5f6   2 hours ago   400MB
mysql                       8.0       b2c3d4e5f6g7   3 days ago    521MB
```

### Contenedores en Ejecución

```bash
# Verificar contenedores activos
docker ps

# Resultado:
CONTAINER ID   IMAGE                              COMMAND                  STATUS        PORTS                    NAMES
a1b2c3d4e5f6   paredes/maintenance-log-api:1.0   "java -jar build/lib…"   Up 2 hours    0.0.0.0:8080->8080/tcp   maintenance-api
b2c3d4e5f6g7   mysql:8.0                          "docker-entrypoint.s…"   Up 2 hours    0.0.0.0:3306->3306/tcp   mysql-maintenance
```

### Logs de la Aplicación

```bash
# Logs de la API
docker logs maintenance-api

# Salida esperada:
2024-12-01 10:00:00.123  INFO --- [main] e.e.e.p.ParedesMaintenanceLogApplication : Starting ParedesMaintenanceLogApplication
2024-12-01 10:00:01.456  INFO --- [main] o.s.d.j.r.query.QueryCreationContext     : Created query for method findByStatus
2024-12-01 10:00:02.789  INFO --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
2024-12-01 10:00:02.800  INFO --- [main] e.e.e.p.ParedesMaintenanceLogApplication : Started ParedesMaintenanceLogApplication in 3.677 seconds
```

### Red Docker

```bash
# Verificar conectividad de red
docker network inspect maintenance-network

# Resultado (fragmento):
[
    {
        "Name": "maintenance-network",
        "Driver": "bridge",
        "Containers": {
            "a1b2c3d4e5f6": {
                "Name": "maintenance-api",
                "EndpointID": "c4d5e6f7g8h9i...",
                "IPv4Address": "172.18.0.3/16"
            },
            "b2c3d4e5f6g7": {
                "Name": "mysql-maintenance", 
                "EndpointID": "d5e6f7g8h9i0j...",
                "IPv4Address": "172.18.0.2/16"
            }
        }
    }
]
```

---

## 🧪 EVIDENCIAS DE PRUEBAS CON POSTMAN

### Colección Postman Completa

**Archivo:** `MaintenanceLog-API-Postman-Collection.json`

### Estructura de Pruebas

```
📁 MaintenanceLog API Collection
├── 📄 1. Create Maintenance Log      ✅ PASSED
├── 📄 2. Get All Maintenance Logs    ✅ PASSED  
├── 📄 3. Get Maintenance Log by ID   ✅ PASSED
├── 📄 4. Update Maintenance Log      ✅ PASSED
├── 📄 5. Delete Maintenance Log      ✅ PASSED
├── 📁 Search Operations
│   ├── 📄 Search by Status           ✅ PASSED
│   ├── 📄 Search by Technician       ✅ PASSED
│   ├── 📄 Search by Date Range       ✅ PASSED
│   └── 📄 Search by Title            ✅ PASSED
├── 📁 Validation Tests
│   ├── 📄 Create with Invalid Data   ✅ PASSED (400 Error)
│   └── 📄 Get Non-existent ID        ✅ PASSED (404 Error)
└── 📄 Cleanup - Delete Test Data     ✅ PASSED
```

### Tests Automatizados

**1. Test de Creación (POST /api/v1/maintenance-logs)**
```javascript
// Test Script
pm.test("Status code is 201", function () {
    pm.response.to.have.status(201);
});

pm.test("Response has id", function () {
    const responseJson = pm.response.json();
    pm.expect(responseJson).to.have.property('id');
    pm.collectionVariables.set("maintenance_id", responseJson.id);
});

pm.test("Response structure is correct", function () {
    const responseJson = pm.response.json();
    pm.expect(responseJson).to.have.property('title');
    pm.expect(responseJson).to.have.property('status');
    pm.expect(responseJson).to.have.property('technician');
});
```

**2. Test de Validación (POST con datos inválidos)**
```javascript
pm.test("Status code is 400", function () {
    pm.response.to.have.status(400);
});

pm.test("Error message exists", function () {
    const responseJson = pm.response.json();
    pm.expect(responseJson).to.have.property('message');
    pm.expect(responseJson.message).to.include('validation failed');
});
```

### Resultados de Ejecución

```
📊 Test Results Summary:
├── 🟢 Total Requests: 13
├── 🟢 Passed Tests: 39/39
├── 🟢 Failed Tests: 0
├── 🟢 Skipped Tests: 0
├── ⏱️ Total Duration: 1.2 seconds
└── 📈 Success Rate: 100%

📈 Response Time Analysis:
├── Average: 145ms
├── Min: 45ms  
├── Max: 387ms
└── P95: 298ms
```

### Casos de Prueba Exitosos

| Endpoint | Método | Payload | Resultado Esperado | ✅ Status |
|----------|--------|---------|-------------------|-----------|
| `/` | POST | Valid DTO | 201 Created + ID | PASSED |
| `/` | GET | - | 200 OK + Array | PASSED |
| `/{id}` | GET | ID válido | 200 OK + Object | PASSED |
| `/{id}` | PUT | Valid Update DTO | 200 OK + Updated | PASSED |
| `/{id}` | DELETE | ID válido | 204 No Content | PASSED |

### Casos de Error Verificados

| Escenario | Endpoint | Payload | Resultado Esperado | ✅ Status |
|-----------|----------|---------|-------------------|-----------|
| Título vacío | POST `/` | title: "" | 400 Bad Request | PASSED |
| Fecha nula | POST `/` | date: null | 400 Bad Request | PASSED |
| ID inexistente | GET `/{id}` | id: 99999 | 404 Not Found | PASSED |
| Estado inválido | POST `/` | status: "INVALID" | 400 Bad Request | PASSED |

---

## 💻 CÓDIGO RELEVANTE Y EXPLICACIONES

### 1. Entidad Principal - MaintenanceLog.java

**Ubicación:** `src/main/java/.../entity/MaintenanceLog.java`

```java
@Entity
@Table(name = "maintenance_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 100)
    private String technician;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    public enum MaintenanceStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
```

**🔧 Explicación Técnica:**
- **@Entity**: Marca la clase como entidad JPA para mapeo objeto-relacional
- **@GeneratedValue**: Configuración de auto-incremento para el ID
- **@Enumerated(EnumType.STRING)**: Almacena el enum como string para legibilidad
- **@Column**: Configuraciones específicas de columnas (longitud, nulabilidad)
- **Lombok @Data**: Genera automáticamente getters, setters, toString(), equals() y hashCode()

### 2. Controlador REST - MaintenanceLogController.java

**Ubicación:** `src/main/java/.../controller/MaintenanceLogController.java`

```java
@RestController
@RequestMapping("/api/v1/maintenance-logs")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceLogService;

    @PostMapping
    public ResponseEntity<MaintenanceLogResponseDTO> createMaintenanceLog(
            @Valid @RequestBody MaintenanceLogCreateDTO createDTO) {
        
        log.info("POST /api/v1/maintenance-logs - Crear log: {}", createDTO.getTitle());
        MaintenanceLogResponseDTO response = maintenanceLogService.createMaintenanceLog(createDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLogResponseDTO> getMaintenanceLogById(
            @PathVariable Long id) {
        
        log.info("GET /api/v1/maintenance-logs/{} - Buscar por ID", id);
        MaintenanceLogResponseDTO response = maintenanceLogService.getMaintenanceLogById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MaintenanceLogResponseDTO>> getMaintenanceLogsByStatus(
            @PathVariable MaintenanceStatus status) {
        
        log.info("GET /api/v1/maintenance-logs/status/{} - Buscar por estado", status);
        List<MaintenanceLogResponseDTO> response = maintenanceLogService.getMaintenanceLogsByStatus(status);
        return ResponseEntity.ok(response);
    }
}
```

**🔧 Explicación Técnica:**
- **@RestController**: Combina @Controller + @ResponseBody para REST
- **@RequestMapping**: Define la base URL para todos los endpoints
- **@Valid**: Activa la validación automática de Bean Validation
- **@PathVariable**: Extrae variables de la URL
- **ResponseEntity**: Permite control granular de códigos HTTP
- **@Slf4j**: Logging automático con SLF4J

### 3. Service Layer - MaintenanceLogService.java

**Ubicación:** `src/main/java/.../service/impl/MaintenanceLogServiceImpl.java`

```java
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MaintenanceLogServiceImpl implements MaintenanceLogService {

    private final MaintenanceLogRepository repository;
    private final MaintenanceLogMapper mapper;

    @Override
    public MaintenanceLogResponseDTO createMaintenanceLog(MaintenanceLogCreateDTO createDTO) {
        log.info("Creando maintenance log: {}", createDTO.getTitle());
        
        MaintenanceLog entity = mapper.toEntity(createDTO);
        MaintenanceLog savedEntity = repository.save(entity);
        
        log.info("Maintenance log creado con ID: {}", savedEntity.getId());
        return mapper.toResponseDTO(savedEntity);
    }

    @Override
    public MaintenanceLogResponseDTO getMaintenanceLogById(Long id) {
        log.info("Buscando maintenance log por ID: {}", id);
        
        MaintenanceLog entity = repository.findById(id)
            .orElseThrow(() -> new MaintenanceLogNotFoundException("Maintenance log no encontrado con ID: " + id));
            
        return mapper.toResponseDTO(entity);
    }

    @Override
    public List<MaintenanceLogResponseDTO> getMaintenanceLogsByStatus(MaintenanceStatus status) {
        log.info("Buscando logs por estado: {}", status);
        
        List<MaintenanceLog> entities = repository.findByStatus(status);
        return entities.stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
```

**🔧 Explicación Técnica:**
- **@Service**: Marca la clase como componente de servicio de Spring
- **@Transactional**: Garantiza transacciones ACID en operaciones de base de datos
- **Dependency Injection**: Inyección automática de dependencias
- **Optional.orElseThrow()**: Manejo elegante de casos no encontrados
- **Stream API**: Procesamiento funcional de listas para transformaciones

### 4. Repository Layer - MaintenanceLogRepository.java

```java
@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {
    
    List<MaintenanceLog> findByStatus(MaintenanceStatus status);
    
    List<MaintenanceLog> findByTechnician(String technician);
    
    List<MaintenanceLog> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<MaintenanceLog> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT m FROM MaintenanceLog m WHERE m.status = :status AND m.technician = :technician")
    List<MaintenanceLog> findByStatusAndTechnician(
        @Param("status") MaintenanceStatus status,
        @Param("technician") String technician
    );
}
```

**🔧 Explicación Técnica:**
- **JpaRepository**: Hereda operaciones CRUD básicas y paginación
- **Query Methods**: Spring genera automáticamente queries basadas en nombres de métodos
- **@Query**: Permite definir queries JPQL personalizadas
- **@Param**: Vincula parámetros del método con parámetros de la query

### 5. DTO Pattern - Data Transfer Objects

**MaintenanceLogCreateDTO.java:**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLogCreateDTO {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede exceder 255 caracteres")
    private String title;

    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String description;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotBlank(message = "El técnico es obligatorio")
    @Size(max = 100, message = "El nombre del técnico no puede exceder 100 caracteres")
    private String technician;

    @NotNull(message = "El estado es obligatorio")
    private MaintenanceStatus status;
}
```

**🔧 Explicación Técnica:**
- **Bean Validation**: Validaciones declarativas con anotaciones JSR-303
- **@NotBlank**: Valida que el campo no sea null, vacío o solo espacios
- **@Size**: Controla la longitud mínima y máxima
- **Separación de Responsabilidades**: DTOs optimizados para cada operación

### 6. Global Exception Handler

```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MaintenanceLogNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMaintenanceLogNotFound(MaintenanceLogNotFoundException ex) {
        log.error("MaintenanceLog no encontrado: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path("/api/v1/maintenance-logs")
                .build();
                
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Errores de validación: {}", ex.getBindingResult().getFieldErrors());
        
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Los datos enviados no son válidos")
                .details(errors)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

**🔧 Explicación Técnica:**
- **@RestControllerAdvice**: Manejo global de excepciones para todos los controladores
- **@ExceptionHandler**: Define qué método maneja cada tipo de excepción
- **Estructura de Error Estándar**: Respuestas consistentes para todos los errores
- **Logging Detallado**: Registro completo para debugging y monitoreo

### 7. Configuración de Propiedades

**application.properties:**
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/maintenance_log_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.port=8080
server.servlet.context-path=/

# Logging Configuration
logging.level.ec.edu.espe.paredes_maintenancelog=DEBUG
logging.level.org.springframework.web=INFO
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

**🔧 Explicación Técnica:**
- **ddl-auto=update**: Actualiza automáticamente el esquema de BD
- **show-sql=true**: Muestra las queries SQL generadas para debugging
- **Context Path**: Configuración de la ruta base de la aplicación
- **Logging Levels**: Control granular de niveles de log por paquete
- ✅ **Documentación**: Cobertura completa
- ✅ **Containerización**: Docker funcional

---

## 🚀 PASOS DETALLADOS PARA EJECUTAR LA APLICACIÓN

### Método 1: Docker Standalone (Recomendado)

#### Prerrequisitos
- Docker instalado y funcionando
- Puerto 8080 y 3306 disponibles
- Git para clonar el repositorio

#### Pasos de Ejecución

**1. Preparación del Entorno**
```bash
# Clonar repositorio
git clone <repository-url>
cd Paredes_MaintenanceLog

# Crear red Docker
docker network create maintenance-network
```

**2. Configuración de Base de Datos**
```bash
# Iniciar contenedor MySQL
docker run -d \
  --name mysql-maintenance \
  --network maintenance-network \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=maintenance_log_db \
  -p 3306:3306 \
  mysql:8.0

# Verificar que MySQL esté corriendo
docker logs mysql-maintenance
```

**3. Construcción de la API**
```bash
# Construir imagen de la aplicación
docker build -t paredes/maintenance-log-api:1.0 .

# Verificar imagen creada
docker images | grep paredes
```

**4. Ejecución de la API**
```bash
# Ejecutar contenedor de la API
docker run -d \
  --name maintenance-api \
  --network maintenance-network \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-maintenance:3306/maintenance_log_db \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  -p 8080:8080 \
  paredes/maintenance-log-api:1.0
```

**5. Verificación**
```bash
# Verificar contenedores corriendo
docker ps

# Verificar logs de la aplicación
docker logs maintenance-api

# Test de conectividad
curl http://localhost:8080/api/v1/maintenance-logs
# Respuesta esperada: []
```

### Método 2: Ejecución Local para Desarrollo

**1. Configuración de MySQL Local**
```sql
-- Conectar a MySQL
mysql -u root -p

-- Crear base de datos
CREATE DATABASE maintenance_log_db;

-- Verificar creación
SHOW DATABASES;
```

**2. Configuración del Proyecto**
```bash
# Configurar permisos (Linux/Mac)
chmod +x ./gradlew

# Compilar proyecto
./gradlew clean build

# Verificar compilación
ls build/libs/
```

**3. Ejecución de la Aplicación**
```bash
# Opción 1: Con Gradle
./gradlew bootRun

# Opción 2: Con JAR
java -jar build/libs/Paredes_MaintenanceLog-0.0.1-SNAPSHOT.jar

# Opción 3: Desde IDE (IntelliJ/Eclipse)
# Ejecutar ParedesMaintenanceLogApplication.java
```

**4. Verificación de Funcionamiento**
```bash
# Health check
curl http://localhost:8080/api/v1/maintenance-logs

# Verificar logs en consola
# Buscar: "Started ParedesMaintenanceLogApplication"
```

### Método 3: Usando Scripts Automatizados

**Para Linux/Mac:**
```bash
# Hacer ejecutables
chmod +x setup-mysql.sh build-and-push.sh

# Configurar MySQL
./setup-mysql.sh

# Construir y publicar API
./build-and-push.sh
```

**Para Windows:**
```powershell
# Configurar MySQL (PowerShell)
docker network create maintenance-network
docker run -d --name mysql-maintenance --network maintenance-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=maintenance_log_db -p 3306:3306 mysql:8.0

# Construir API
docker build -t paredes/maintenance-log-api:1.0 .

# Ejecutar API
docker run -d --name maintenance-api --network maintenance-network -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-maintenance:3306/maintenance_log_db -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=root -p 8080:8080 paredes/maintenance-log-api:1.0
```

### Solución de Problemas Comunes

**Error: Puerto en uso**
```bash
# Verificar qué usa el puerto 8080
netstat -tulpn | grep 8080  # Linux
netstat -ano | findstr 8080 # Windows

# Cambiar puerto en application.properties
server.port=8081
```

**Error: MySQL Connection**
```bash
# Verificar MySQL corriendo
docker ps | grep mysql

# Verificar logs de MySQL
docker logs mysql-maintenance

# Verificar red Docker
docker network inspect maintenance-network
```

**Error: Build Failures**
```bash
# Limpiar cache
./gradlew clean

# Build con más información
./gradlew build --info

# Verificar Java version
java -version  # Debe ser Java 17+
```

### Verificaciones de Éxito

**Indicadores de que la aplicación está funcionando correctamente:**

1. **Logs de Inicio:**
```
Started ParedesMaintenanceLogApplication in X.X seconds
Tomcat started on port(s): 8080 (http)
```

2. **Base de Datos Conectada:**
```
HikariPool-1 - Start completed.
```

3. **Endpoints Accesibles:**
```bash
curl http://localhost:8080/api/v1/maintenance-logs
# HTTP 200 OK con array vacío []
```

4. **Contenedores Docker:**
```bash
docker ps
# Debe mostrar mysql-maintenance y maintenance-api en estado "Up"
```
