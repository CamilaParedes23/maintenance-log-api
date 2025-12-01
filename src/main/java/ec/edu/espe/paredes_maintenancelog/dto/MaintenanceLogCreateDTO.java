package ec.edu.espe.paredes_maintenancelog.dto;

import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog.MaintenanceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para crear un nuevo MaintenanceLog
 *
 * @author Paredes
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLogCreateDTO {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    private String description;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotBlank(message = "El técnico es obligatorio")
    private String technician;

    @NotNull(message = "El estado es obligatorio")
    private MaintenanceStatus status;
}
