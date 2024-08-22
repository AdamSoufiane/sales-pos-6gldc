package ai.shreds.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception class for handling errors in the DomainCategoryService.
 */
public class DomainCategoryServiceException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(DomainCategoryServiceException.class);
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DomainCategoryServiceException with the specified detail message.
     *
     * @param message the detail message.
     */
    public DomainCategoryServiceException(String message) {
        super(message);
        logger.error(message);
    }

    /**
     * Constructs a new DomainCategoryServiceException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public DomainCategoryServiceException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }

    /**
     * Constructs a new DomainCategoryServiceException with the specified cause.
     *
     * @param cause the cause of the exception.
     */
    public DomainCategoryServiceException(Throwable cause) {
        super(cause);
        logger.error(cause.getMessage(), cause);
    }
}