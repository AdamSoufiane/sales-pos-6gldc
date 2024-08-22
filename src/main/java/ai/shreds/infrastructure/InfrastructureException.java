package ai.shreds.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.apache.kafka.common.KafkaException;

/**
 * Global exception handler for the infrastructure layer.
 */
@RestControllerAdvice
public class InfrastructureException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureException.class);

    /**
     * Handles general exceptions.
     *
     * @param e the exception
     * @return a standardized error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // Log the exception details
        logger.error("An error occurred: ", e);

        // Create a standardized error response
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An internal error occurred. Please try again later.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles database access exceptions.
     *
     * @param e the DataAccessException
     * @return a standardized error response
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException e) {
        // Log the exception details
        logger.error("A database error occurred: ", e);

        // Create a standardized error response
        ErrorResponse errorResponse = new ErrorResponse("DATABASE_ERROR", "A database error occurred. Please contact support.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles Kafka exceptions.
     *
     * @param e the KafkaException
     * @return a standardized error response
     */
    @ExceptionHandler(KafkaException.class)
    public ResponseEntity<ErrorResponse> handleKafkaException(KafkaException e) {
        // Log the exception details
        logger.error("A Kafka error occurred: ", e);

        // Create a standardized error response
        ErrorResponse errorResponse = new ErrorResponse("KAFKA_ERROR", "A messaging error occurred. Please try again later.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Custom error response class.
     */
    public static class ErrorResponse {
        private String errorCode;
        private String errorMessage;

        public ErrorResponse(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}