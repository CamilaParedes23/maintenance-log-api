package ec.edu.espe.paredes_maintenancelog.exception;


public class MaintenanceLogNotFoundException extends RuntimeException {

    public MaintenanceLogNotFoundException(String message) {
        super(message);
    }

    public MaintenanceLogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
