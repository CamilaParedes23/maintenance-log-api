#!/bin/bash
# Script para construir la imagen de la API (Linux/Mac) - Sin Docker Compose
# Autor: Paredes
# Versión: 1.0

# Variables
IMAGE_NAME="paredes/maintenance-log-api"
VERSION="1.0"

echo "🚀 Construyendo API RESTful MaintenanceLog (Standalone)..."

# Limpiar y construir el proyecto
echo "📦 Limpiando y construyendo proyecto Spring Boot..."
./gradlew clean build -x test

if [ $? -ne 0 ]; then
    echo "❌ Error al construir el proyecto Spring Boot"
    exit 1
fi

# Construir imagen Docker
echo "🐳 Construyendo imagen Docker..."
docker build -t $IMAGE_NAME:$VERSION -t $IMAGE_NAME:latest .

if [ $? -ne 0 ]; then
    echo "❌ Error al construir la imagen Docker"
    exit 1
fi

echo "✅ Imagen Docker construida exitosamente"

# Mostrar información de la imagen
echo "📋 Información de la imagen:"
docker images | grep $IMAGE_NAME

echo ""
echo "🎉 ¡Construcción completada!"
echo ""
echo "📚 Opciones de ejecución:"
echo ""
echo "1. Con base de datos externa:"
echo "   docker run -p 8080:8080 \\"
echo "          -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/maintenance_log_db \\"
echo "          -e SPRING_DATASOURCE_USERNAME=root \\"
echo "          -e SPRING_DATASOURCE_PASSWORD=root \\"
echo "          $IMAGE_NAME:$VERSION"
echo ""
echo "2. Con MySQL en otro contenedor:"
echo "   docker network create maintenance-network"
echo "   docker run -d --name mysql-db --network maintenance-network \\"
echo "          -e MYSQL_ROOT_PASSWORD=root \\"
echo "          -e MYSQL_DATABASE=maintenance_log_db \\"
echo "          -p 3306:3306 mysql:8.0"
echo "   docker run -p 8080:8080 --network maintenance-network \\"
echo "          -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/maintenance_log_db \\"
echo "          -e SPRING_DATASOURCE_USERNAME=root \\"
echo "          -e SPRING_DATASOURCE_PASSWORD=root \\"
echo "          $IMAGE_NAME:$VERSION"
echo ""
echo "💡 Para publicar en Docker Hub: docker push $IMAGE_NAME:$VERSION && docker push $IMAGE_NAME:latest"
