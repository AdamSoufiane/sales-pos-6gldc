package ai.shreds.infrastructure;

import ai.shreds.domain.SupplierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class InfrastructureExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureExceptionHandler.class);

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<String> handleSupplierNotFoundException(ai.shreds.domain.SupplierNotFoundException e) {
        logger.error("Supplier not found: ", e);
        return new ResponseEntity<>("Supplier not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        logger.error("Internal server error: ", e);
        return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}