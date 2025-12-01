package ec.edu.espe.paredes_maintenancelog.dto;

import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog.MaintenanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO de respuesta para MaintenanceLog
 *
 * @author Paredes
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLogResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String technician;
    private MaintenanceStatus status;
}
