package ai.shreds.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataAccessException;
import javax.validation.ConstraintViolationException;
import ai.shreds.domain.DomainCategoryException;
import lombok.Getter;
import lombok.Setter;

@RestControllerAdvice
public class InfrastructureCategoryException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // Log the exception details
        logger.error("Exception occurred: ", e);

        // Convert the exception into a custom error response
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        // Return the error response
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException e) {
        // Log the exception details
        logger.error("DataAccessException occurred: ", e);

        // Convert the exception into a custom error response
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Database error: " + e.getMessage());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        // Return the error response
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        // Log the exception details
        logger.error("ConstraintViolationException occurred: ", e);

        // Convert the exception into a custom error response
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Validation error: " + e.getMessage());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        // Return the error response
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainCategoryException.class)
    public ResponseEntity<ErrorResponse> handleDomainCategoryException(DomainCategoryException e) {
        // Log the exception details
        logger.error("DomainCategoryException occurred: ", e);

        // Convert the exception into a custom error response
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Domain error: " + e.getMessage());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        // Return the error response
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Custom error response class
    @Getter
    @Setter
    public static class ErrorResponse {
        private int statusCode;
        private String message;
    }
}