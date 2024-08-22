package ai.shreds.application;

public class ApplicationCategoryServiceException extends RuntimeException {

    /**
     * Constructs a new ApplicationCategoryServiceException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ApplicationCategoryServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new ApplicationCategoryServiceException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public ApplicationCategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}