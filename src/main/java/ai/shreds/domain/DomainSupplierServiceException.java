package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Custom exception class for handling exceptions specific to the Domain layer of the Supplier feature.
 * This class extends RuntimeException and provides constructors for creating exceptions with different levels of detail.
 */
@NoArgsConstructor
@AllArgsConstructor
public class DomainSupplierServiceException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * @param message the detail message.
     */
    public DomainSupplierServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DomainSupplierServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * @param cause the cause (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DomainSupplierServiceException(Throwable cause) {
        super(cause);
    }
}