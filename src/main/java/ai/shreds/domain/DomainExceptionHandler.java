package ai.shreds.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class DomainExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DomainExceptionHandler.class);

    /**
     * Handles general exceptions that occur within the domain layer.
     * Logs the exception details and returns an appropriate HTTP response.
     *
     * @param exception the exception that occurred
     * @return a ResponseEntity containing an error message and HTTP status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        // Log the exception details
        logger.error("Exception occurred: ", exception);

        // Determine the type of exception and provide a customized response
        String errorMessage = "An unexpected error occurred.";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        // Customize the response based on different exception types if needed
        if (exception instanceof IllegalArgumentException) {
            errorMessage = "Invalid input provided.";
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof IllegalStateException) {
            errorMessage = "Operation cannot be performed in the current state.";
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(errorMessage, status);
    }
}