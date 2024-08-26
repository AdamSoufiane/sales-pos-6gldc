package ai.shreds.domain;

/**
 * Custom exception class for handling cases where a Supplier entity is not found.
 * This class extends RuntimeException and provides a constructor for creating exceptions with a detailed message.
 */
public class SupplierNotFoundException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * @param message the detail message.
     */
    public SupplierNotFoundException(String message) {
        super(message);
    }
}