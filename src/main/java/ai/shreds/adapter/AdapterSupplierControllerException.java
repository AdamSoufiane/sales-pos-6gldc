package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class AdapterSupplierControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterSupplierControllerException.class);

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<AdapterSupplierControllerException.ErrorResponse> handleSupplierNotFoundException(SupplierNotFoundException e) {
        logger.error("Supplier not found exception: ", e);
        return new ResponseEntity<>(new AdapterSupplierControllerException.ErrorResponse("404", "Supplier not found: " + e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterSupplierControllerException.ErrorResponse> handleException(Exception e) {
        logger.error("Internal server error: ", e);
        return new ResponseEntity<>(new AdapterSupplierControllerException.ErrorResponse("500", "Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static final class ErrorResponse {
        private final String errorCode;
        private final String errorMessage;

        public ErrorResponse(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}

class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String message) {
        super(message);
    }
}