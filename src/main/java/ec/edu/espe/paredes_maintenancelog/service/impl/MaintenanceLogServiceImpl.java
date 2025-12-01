package ec.edu.espe.paredes_maintenancelog.service.impl;

import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogCreateDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogResponseDTO;
import ec.edu.espe.paredes_maintenancelog.dto.MaintenanceLogUpdateDTO;
import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog;
import ec.edu.espe.paredes_maintenancelog.entity.MaintenanceLog.MaintenanceStatus;
import ec.edu.espe.paredes_maintenancelog.exception.MaintenanceLogNotFoundException;
import ec.edu.espe.paredes_maintenancelog.mapper.MaintenanceLogMapper;
import ec.edu.espe.paredes_maintenancelog.repository.MaintenanceLogRepository;
import ec.edu.espe.paredes_maintenancelog.service.MaintenanceLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de MaintenanceLog
 *
 * @author Paredes
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MaintenanceLogServiceImpl implements MaintenanceLogService {

    private final MaintenanceLogRepository maintenanceLogRepository;
    private final MaintenanceLogMapper maintenanceLogMapper;

    @Override
    public MaintenanceLogResponseDTO createMaintenanceLog(MaintenanceLogCreateDTO createDTO) {
        log.info("Creando nuevo log de mantenimiento: {}", createDTO.getTitle());

        MaintenanceLog maintenanceLog = maintenanceLogMapper.toEntity(createDTO);
        MaintenanceLog savedLog = maintenanceLogRepository.save(maintenanceLog);

        log.info("Log de mantenimiento creado con ID: {}", savedLog.getId());
        return maintenanceLogMapper.toResponseDTO(savedLog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceLogResponseDTO> getAllMaintenanceLogs() {
        log.info("Obteniendo todos los logs de mantenimiento");

        List<MaintenanceLog> logs = maintenanceLogRepository.findAll();
        return logs.stream()
                .map(maintenanceLogMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MaintenanceLogResponseDTO getMaintenanceLogById(Long id) {
        log.info("Buscando log de mantenimiento con ID: {}", id);

        MaintenanceLog maintenanceLog = maintenanceLogRepository.findById(id)
                .orElseThrow(() -> new MaintenanceLogNotFoundException(
                        "Log de mantenimiento no encontrado con ID: " + id));

        return maintenanceLogMapper.toResponseDTO(maintenanceLog);
    }

    @Override
    public MaintenanceLogResponseDTO updateMaintenanceLog(Long id, MaintenanceLogUpdateDTO updateDTO) {
        log.info("Actualizando log de mantenimiento con ID: {}", id);

        MaintenanceLog existingLog = maintenanceLogRepository.findById(id)
                .orElseThrow(() -> new MaintenanceLogNotFoundException(
                        "Log de mantenimiento no encontrado con ID: " + id));

        maintenanceLogMapper.updateEntity(existingLog, updateDTO);
        MaintenanceLog updatedLog = maintenanceLogRepository.save(existingLog);

        log.info("Log de mantenimiento actualizado con ID: {}", updatedLog.getId());
        return maintenanceLogMapper.toResponseDTO(updatedLog);
    }

    @Override
    public void deleteMaintenanceLog(Long id) {
        log.info("Eliminando log de mantenimiento con ID: {}", id);

        if (!maintenanceLogRepository.existsById(id)) {
            throw new MaintenanceLogNotFoundException(
                    "Log de mantenimiento no encontrado con ID: " + id);
        }

        maintenanceLogRepository.deleteById(id);
        log.info("Log de mantenimiento eliminado con ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceLogResponseDTO> getMaintenanceLogsByStatus(MaintenanceStatus status) {
        log.info("Buscando logs por estado: {}", status);

        List<MaintenanceLog> logs = maintenanceLogRepository.findByStatus(status);
        return logs.stream()
                .map(maintenanceLogMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceLogResponseDTO> getMaintenanceLogsByTechnician(String technician) {
        log.info("Buscando logs por técnico: {}", technician);

        List<MaintenanceLog> logs = maintenanceLogRepository.findByTechnicianContainingIgnoreCase(technician);
        return logs.stream()
                .map(maintenanceLogMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceLogResponseDTO> getMaintenanceLogsByDateRange(LocalDate startDate, LocalDate endDate) {
        log.info("Buscando logs entre fechas: {} y {}", startDate, endDate);

        List<MaintenanceLog> logs = maintenanceLogRepository.findByDateBetween(startDate, endDate);
        return logs.stream()
                .map(maintenanceLogMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceLogResponseDTO> getMaintenanceLogsByTitle(String title) {
        log.info("Buscando logs por título: {}", title);

        List<MaintenanceLog> logs = maintenanceLogRepository.findByTitleContainingIgnoreCase(title);
        return logs.stream()
                .map(maintenanceLogMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
