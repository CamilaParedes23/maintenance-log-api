# Dockerfile para MaintenanceLog API - Versión Standalone
FROM eclipse-temurin:17-jdk-alpine

# Información del mantenedor
LABEL maintainer="paredes@espe.edu.ec"
LABEL version="1.0"
LABEL description="API RESTful para gestión de logs de mantenimiento - Standalone"

# Instalar dependencias necesarias
RUN apk add --no-cache bash

# Crear directorio de trabajo
WORKDIR /app

# Copiar los archivos de Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Hacer el gradlew ejecutable
RUN chmod +x ./gradlew

# Copiar el código fuente
COPY src src

# Construir la aplicación
RUN ./gradlew build -x test

# Crear usuario no-root para seguridad
RUN addgroup -g 1000 appgroup && adduser -u 1000 -G appgroup -s /bin/sh -D appuser
RUN chown -R appuser:appgroup /app
USER appuser

# Exponer el puerto
EXPOSE 8080

# Variables de entorno por defecto (pueden ser sobrescritas al ejecutar)
ENV SPRING_PROFILES_ACTIVE=docker
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/maintenance_log_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "build/libs/Paredes_MaintenanceLog-0.0.1-SNAPSHOT.jar"]
