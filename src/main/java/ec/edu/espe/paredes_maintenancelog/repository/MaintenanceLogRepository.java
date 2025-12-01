package ec.edu.espe.paredes_maintenancelog.repository;

import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog;
import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository para operaciones CRUD de MaintenanceLog
 *
 * @author Paredes
 * @version 1.0
 */
@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {

    /**
     * Buscar logs por estado
     */
    List<MaintenanceLog> findByStatus(MaintenanceStatus status);

    /**
     * Buscar logs por técnico
     */
    List<MaintenanceLog> findByTechnicianContainingIgnoreCase(String technician);

    /**
     * Buscar logs por rango de fechas
     */
    List<MaintenanceLog> findByDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Buscar logs por título que contenga texto
     */
    List<MaintenanceLog> findByTitleContainingIgnoreCase(String title);

    /**
     * Contar logs por estado
     */
    @Query("SELECT COUNT(m) FROM MaintenanceLog m WHERE m.status = :status")
    Long countByStatus(@Param("status") MaintenanceStatus status);
}
