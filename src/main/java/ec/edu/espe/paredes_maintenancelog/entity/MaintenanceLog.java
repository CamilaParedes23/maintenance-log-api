package ec.edu.espe.paredes_maintenancelog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidad MaintenanceLog para gestión de logs de mantenimiento
 *
 * @author Paredes
 * @version 1.0
 */
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

    /**
     * Enum para los estados del mantenimiento
     */
    public enum MaintenanceStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }
}
