package ai.shreds.infrastructure;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception class to handle infrastructure-related exceptions.
 */
@Getter
@Setter
public class InfrastructureException extends RuntimeException {

    /**
     * Constructor with message parameter.
     *
     * @param message the detail message.
     */
    public InfrastructureException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause parameters.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}