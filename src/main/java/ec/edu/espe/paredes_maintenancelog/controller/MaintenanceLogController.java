package ec.edu.espe.paredes_maintenancelog.controller;

import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogCreateDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogResponseDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogUpdateDTO;
import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog.MaintenanceStatus;
import ec.edu.espe.paredes_maintenancelog.service.MaintenanceLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para MaintenanceLog
 *
 * @author Paredes
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/maintenance-logs")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceLogService;

    /**
     * Crear un nuevo log de mantenimiento
     */
    @PostMapping
    public ResponseEntity<MaintenanceLogResponseDTO> createMaintenanceLog(
            @Valid @RequestBody MaintenanceLogCreateDTO createDTO) {

        log.info("POST /api/v1/maintenance-logs - Crear log: {}", createDTO.getTitle());
        MaintenanceLogResponseDTO response = maintenanceLogService.createMaintenanceLog(createDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Obtener todos los logs de mantenimiento
     */
    @GetMapping
    public ResponseEntity<List<MaintenanceLogResponseDTO>> getAllMaintenanceLogs() {
        log.info("GET /api/v1/maintenance-logs - Obtener todos los logs");
        List<MaintenanceLogResponseDTO> logs = maintenanceLogService.getAllMaintenanceLogs();
        return ResponseEntity.ok(logs);
    }

    /**
     * Obtener un log de mantenimiento por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLogResponseDTO> getMaintenanceLogById(@PathVariable Long id) {
        log.info("GET /api/v1/maintenance-logs/{} - Obtener log por ID", id);
        MaintenanceLogResponseDTO log = maintenanceLogService.getMaintenanceLogById(id);
        return ResponseEntity.ok(log);
    }

    /**
     * Actualizar un log de mantenimiento
     */
    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceLogResponseDTO> updateMaintenanceLog(
            @PathVariable Long id,
            @RequestBody MaintenanceLogUpdateDTO updateDTO) {

        log.info("PUT /api/v1/maintenance-logs/{} - Actualizar log", id);
        MaintenanceLogResponseDTO response = maintenanceLogService.updateMaintenanceLog(id, updateDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Eliminar un log de mantenimiento
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceLog(@PathVariable Long id) {
        log.info("DELETE /api/v1/maintenance-logs/{} - Eliminar log", id);
        maintenanceLogService.deleteMaintenanceLog(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar logs por estado
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<MaintenanceLogResponseDTO>> getMaintenanceLogsByStatus(
            @PathVariable MaintenanceStatus status) {

        log.info("GET /api/v1/maintenance-logs/status/{} - Buscar por estado", status);
        List<MaintenanceLogResponseDTO> logs = maintenanceLogService.getMaintenanceLogsByStatus(status);
        return ResponseEntity.ok(logs);
    }

    /**
     * Buscar logs por técnico
     */
    @GetMapping("/technician/{technician}")
    public ResponseEntity<List<MaintenanceLogResponseDTO>> getMaintenanceLogsByTechnician(
            @PathVariable String technician) {

        log.info("GET /api/v1/maintenance-logs/technician/{} - Buscar por técnico", technician);
        List<MaintenanceLogResponseDTO> logs = maintenanceLogService.getMaintenanceLogsByTechnician(technician);
        return ResponseEntity.ok(logs);
    }

    /**
     * Buscar logs por rango de fechas
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<MaintenanceLogResponseDTO>> getMaintenanceLogsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        log.info("GET /api/v1/maintenance-logs/date-range - Buscar entre {} y {}", startDate, endDate);
        List<MaintenanceLogResponseDTO> logs = maintenanceLogService.getMaintenanceLogsByDateRange(startDate, endDate);
        return ResponseEntity.ok(logs);
    }

    /**
     * Buscar logs por título
     */
    @GetMapping("/search")
    public ResponseEntity<List<MaintenanceLogResponseDTO>> getMaintenanceLogsByTitle(
            @RequestParam String title) {

        log.info("GET /api/v1/maintenance-logs/search - Buscar por título: {}", title);
        List<MaintenanceLogResponseDTO> logs = maintenanceLogService.getMaintenanceLogsByTitle(title);
        return ResponseEntity.ok(logs);
    }
}
