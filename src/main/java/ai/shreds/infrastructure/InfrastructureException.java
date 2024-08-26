package ai.shreds.infrastructure;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ai.shreds.domain.SupplierNotFoundException;

@ControllerAdvice
public class InfrastructureException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureException.class);

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e, WebRequest request) {
        logger.error("Database error occurred: ", e);
        return new ResponseEntity<>("Database error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ai.shreds.domain.SupplierNotFoundException.class)
    public ResponseEntity<String> handleSupplierNotFoundException(ai.shreds.domain.SupplierNotFoundException e, WebRequest request) {
        logger.error("Supplier not found: ", e);
        return new ResponseEntity<>("Supplier not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e, WebRequest request) {
        logger.error("An error occurred: ", e);
        return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}