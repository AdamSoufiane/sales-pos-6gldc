package ai.shreds.shared;

import java.util.UUID;

/**
 * Port for sending notifications.
 */
public interface AlertServicePort {
    /**
     * Sends a notification message for a specific product.
     *
     * @param productId The UUID of the product.
     * @param message The notification message to be sent.
     */
    void sendNotification(UUID productId, String message);
}