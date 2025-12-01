package ec.edu.espe.paredes_maintenancelog.exception;

/**
 * Excepción personalizada para cuando no se encuentra un MaintenanceLog
 *
 * @author Paredes
 * @version 1.0
 */
public class MaintenanceLogNotFoundException extends RuntimeException {

    public MaintenanceLogNotFoundException(String message) {
        super(message);
    }

    public MaintenanceLogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
