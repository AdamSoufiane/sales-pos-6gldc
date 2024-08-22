package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.Getter;
import lombok.Setter;

@RestControllerAdvice
public class InfrastructureCategoryRepositoryImplException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImplException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // Log the exception details
        logger.error("Exception occurred: ", e);

        // Create an ErrorResponse object with the error message
        ErrorResponse errorResponse = new ErrorResponse("An error occurred while processing your request.");

        // Return the ErrorResponse wrapped in a ResponseEntity with the appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException e) {
        // Log the exception details
        logger.error("Data access exception occurred: ", e);

        // Create an ErrorResponse object with the error message
        ErrorResponse errorResponse = new ErrorResponse("A database error occurred while processing your request.");

        // Return the ErrorResponse wrapped in a ResponseEntity with the appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DomainCategoryException.class)
    public ResponseEntity<ErrorResponse> handleDomainCategoryException(DomainCategoryException e) {
        // Log the exception details
        logger.error("Domain category exception occurred: ", e);

        // Create an ErrorResponse object with the error message
        ErrorResponse errorResponse = new ErrorResponse("A domain error occurred while processing your request.");

        // Return the ErrorResponse wrapped in a ResponseEntity with the appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Inner class to represent the error response structure
    @Getter
    @Setter
    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }
}