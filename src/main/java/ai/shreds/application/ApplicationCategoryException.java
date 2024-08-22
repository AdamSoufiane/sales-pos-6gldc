package ai.shreds.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationCategoryException {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // Log the exception details
        logger.error("An error occurred: ", e);
        // Create an ErrorResponse object
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", e.getMessage());
        // Return a ResponseEntity with the error response and appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryOperationException.class)
    public ResponseEntity<ErrorResponse> handleCategoryOperationException(CategoryOperationException e) {
        // Log the exception details
        logger.error("A category operation error occurred: ", e);
        // Create an ErrorResponse object
        ErrorResponse errorResponse = new ErrorResponse("Category Operation Error", e.getMessage());
        // Return a ResponseEntity with the error response and appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
        // Log the exception details
        logger.error("A validation error occurred: ", e);
        // Create an ErrorResponse object
        ErrorResponse errorResponse = new ErrorResponse("Validation Error", e.getMessage());
        // Return a ResponseEntity with the error response and appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}