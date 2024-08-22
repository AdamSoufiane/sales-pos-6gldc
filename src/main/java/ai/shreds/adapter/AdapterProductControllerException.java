package ai.shreds.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdapterProductControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductControllerException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception details
        logger.error("Exception occurred: ", e);

        // Check for specific exception types and return appropriate responses
        if (e instanceof ProductNotFoundException) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        } else if (e instanceof IllegalArgumentException) {
            return new ResponseEntity<>("Invalid argument provided", HttpStatus.BAD_REQUEST);
        } else if (e instanceof NullPointerException) {
            return new ResponseEntity<>("A required value was null", HttpStatus.BAD_REQUEST);
        }

        // Default to internal server error for other exceptions
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Custom exception class for Product Not Found
    static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}