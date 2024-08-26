package ai.shreds.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class for handling exceptions that occur during the processing of 'ProductAdded' events.
 */
public class ApplicationProductAddedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationProductAddedException.class);

    /**
     * Constructor with message only.
     * @param message the exception message
     */
    public ApplicationProductAddedException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
     * @param message the exception message
     * @param cause the underlying cause of the exception
     */
    public ApplicationProductAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Handles the exception by logging the error details and throwing a ResponseStatusException.
     * @param cause the underlying cause of the exception
     */
    public void handleException(Throwable cause) {
        // Log the exception details
        logger.error("Exception occurred: {}", cause.getMessage(), cause);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage(), cause);
    }
}