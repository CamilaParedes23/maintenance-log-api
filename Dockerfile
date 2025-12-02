# ---------- ETAPA 1: BUILD ----------
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# Copiar solo archivos necesarios para descargar dependencias primero
COPY gradlew .
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .

# Permisos
RUN chmod +x ./gradlew

# Pre-descargar dependencias usando el cache (no compila)
RUN ./gradlew build -x test --no-daemon || true

# Copiar el código fuente (esto invalida la cache solo si cambia src/)
COPY src src

# Compilar la aplicación
RUN ./gradlew build -x test --no-daemon


# ---------- ETAPA 2: RUN ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar solo el .jar generado, imagen más ligera
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar

# Crear usuario no-root
RUN addgroup -g 1000 appgroup && adduser -u 1000 -G appgroup -s /bin/sh -D appuser
USER appuser

EXPOSE 8080

# Variables de entorno por defecto
ENV SPRING_PROFILES_ACTIVE=docker
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-maintenance:3306/maintenance_log_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
