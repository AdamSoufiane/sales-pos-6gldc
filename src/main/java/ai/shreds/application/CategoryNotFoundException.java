package ai.shreds.application;

/**
 * CategoryNotFoundException is thrown when a category to be deleted does not exist.
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Constructs a new CategoryNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}