package ec.edu.espe.paredes_maintenancelog.service;

import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogCreateDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogResponseDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogUpdateDTO;
import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog.MaintenanceStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface para el servicio de MaintenanceLog
 *
 * @author Paredes
 * @version 1.0
 */
public interface MaintenanceLogService {

    /**
     * Crear un nuevo log de mantenimiento
     */
    MaintenanceLogResponseDTO createMaintenanceLog(MaintenanceLogCreateDTO createDTO);

    /**
     * Obtener todos los logs de mantenimiento
     */
    List<MaintenanceLogResponseDTO> getAllMaintenanceLogs();

    /**
     * Obtener un log de mantenimiento por ID
     */
    MaintenanceLogResponseDTO getMaintenanceLogById(Long id);

    /**
     * Actualizar un log de mantenimiento
     */
    MaintenanceLogResponseDTO updateMaintenanceLog(Long id, MaintenanceLogUpdateDTO updateDTO);

    /**
     * Eliminar un log de mantenimiento
     */
    void deleteMaintenanceLog(Long id);

    /**
     * Buscar logs por estado
     */
    List<MaintenanceLogResponseDTO> getMaintenanceLogsByStatus(MaintenanceStatus status);

    /**
     * Buscar logs por técnico
     */
    List<MaintenanceLogResponseDTO> getMaintenanceLogsByTechnician(String technician);

    /**
     * Buscar logs por rango de fechas
     */
    List<MaintenanceLogResponseDTO> getMaintenanceLogsByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Buscar logs por título
     */
    List<MaintenanceLogResponseDTO> getMaintenanceLogsByTitle(String title);
}
