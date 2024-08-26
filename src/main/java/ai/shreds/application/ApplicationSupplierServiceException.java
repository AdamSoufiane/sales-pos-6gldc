package ai.shreds.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
ai.shreds.domain.DomainSupplierServiceException;
ai.shreds.infrastructure.InfrastructureException;

@ControllerAdvice
public class ApplicationSupplierServiceException {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSupplierServiceException.class);

    @ExceptionHandler(DomainSupplierServiceException.class)
    public ResponseEntity<ErrorResponse> handleDomainSupplierServiceException(DomainSupplierServiceException e) {
        logger.error("Supplier not found.", e);
        return new ResponseEntity<>(new ErrorResponse("Supplier not found."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ai.shreds.infrastructure.InfrastructureException.class)
    public ResponseEntity<ErrorResponse> handleInfrastructureException(InfrastructureException e) {
        logger.error("Internal server error.", e);
        return new ResponseEntity<>(new ErrorResponse("Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        logger.error("Internal server error.", e);
        return new ResponseEntity<>(new ErrorResponse("Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}