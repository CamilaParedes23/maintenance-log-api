package ec.edu.espe.paredes_maintenancelog.mapper;

import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogCreateDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogResponseDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogUpdateDTO;
import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog;
import org.springframework.stereotype.Component;

/**
 * Mapper para conversión entre MaintenanceLog y DTOs
 *
 * @author Paredes
 * @version 1.0
 */
@Component
public class MaintenanceLogMapper {

    /**
     * Convierte CreateDTO a entidad
     */
    public MaintenanceLog toEntity(MaintenanceLogCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }

        MaintenanceLog maintenanceLog = new MaintenanceLog();
        maintenanceLog.setTitle(createDTO.getTitle());
        maintenanceLog.setDescription(createDTO.getDescription());
        maintenanceLog.setDate(createDTO.getDate());
        maintenanceLog.setTechnician(createDTO.getTechnician());
        maintenanceLog.setStatus(createDTO.getStatus());

        return maintenanceLog;
    }

    /**
     * Convierte entidad a ResponseDTO
     */
    public MaintenanceLogResponseDTO toResponseDTO(MaintenanceLog maintenanceLog) {
        if (maintenanceLog == null) {
            return null;
        }

        MaintenanceLogResponseDTO responseDTO = new MaintenanceLogResponseDTO();
        responseDTO.setId(maintenanceLog.getId());
        responseDTO.setTitle(maintenanceLog.getTitle());
        responseDTO.setDescription(maintenanceLog.getDescription());
        responseDTO.setDate(maintenanceLog.getDate());
        responseDTO.setTechnician(maintenanceLog.getTechnician());
        responseDTO.setStatus(maintenanceLog.getStatus());

        return responseDTO;
    }

    /**
     * Actualiza entidad con datos del UpdateDTO
     */
    public void updateEntity(MaintenanceLog maintenanceLog, MaintenanceLogUpdateDTO updateDTO) {
        if (updateDTO.getTitle() != null) {
            maintenanceLog.setTitle(updateDTO.getTitle());
        }
        if (updateDTO.getDescription() != null) {
            maintenanceLog.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getDate() != null) {
            maintenanceLog.setDate(updateDTO.getDate());
        }
        if (updateDTO.getTechnician() != null) {
            maintenanceLog.setTechnician(updateDTO.getTechnician());
        }
        if (updateDTO.getStatus() != null) {
            maintenanceLog.setStatus(updateDTO.getStatus());
        }
    }
}
