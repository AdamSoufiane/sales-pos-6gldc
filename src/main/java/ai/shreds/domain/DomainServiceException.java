package ai.shreds.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomainServiceException {

    private static final Logger logger = LoggerFactory.getLogger(DomainServiceException.class);

    public void handleException(Exception e) {
        // Log the exception
        logger.error("An error occurred in the domain service: ", e);

        // Depending on the type of exception, you might want to perform different actions
        if (e instanceof IllegalArgumentException) {
            // Handle specific exception type
            logger.warn("Illegal argument: " + e.getMessage());
        } else if (e instanceof IllegalStateException) {
            // Handle another specific exception type
            logger.warn("Illegal state: " + e.getMessage());
        } else if (e instanceof NullPointerException) {
            // Handle null pointer exceptions
            logger.error("Null pointer exception: " + e.getMessage());
        } else if (e instanceof RuntimeException) {
            // Handle runtime exceptions
            logger.error("Runtime exception: " + e.getMessage());
        } else {
            // For other exceptions, you might want to rethrow them or handle them differently
            throw new RuntimeException("Unexpected error occurred: " + e.getMessage(), e);
        }
    }
}