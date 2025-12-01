-- Script de inicialización para la base de datos maintenance_log_db
-- Autor: Paredes
-- Versión: 1.0

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS maintenance_log_db;

-- Usar la base de datos
USE maintenance_log_db;

-- Crear usuario adicional para la aplicación (opcional)
-- CREATE USER IF NOT EXISTS 'app_user'@'%' IDENTIFIED BY 'app_password';
-- GRANT ALL PRIVILEGES ON maintenance_log_db.* TO 'app_user'@'%';

-- Insertar datos de prueba (opcional)
-- INSERT INTO maintenance_logs (title, description, date, technician, status)
-- VALUES
--   ('Mantenimiento preventivo servidor', 'Revisión general del servidor principal', '2024-01-15', 'Juan Pérez', 'COMPLETED'),
--   ('Actualización sistema operativo', 'Actualización de seguridad del SO', '2024-01-16', 'María García', 'IN_PROGRESS'),
--   ('Respaldo base de datos', 'Respaldo completo de la base de datos', '2024-01-17', 'Carlos López', 'PENDING');

FLUSH PRIVILEGES;
