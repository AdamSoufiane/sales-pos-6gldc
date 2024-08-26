package ai.shreds.infrastructure;

import ai.shreds.shared.AlertServicePort;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of the AlertServicePort interface.
 * This class is responsible for sending notifications.
 */
@Service
public class InfrastructureAlertServiceImpl implements AlertServicePort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureAlertServiceImpl.class);

    /**
     * Sends a notification message for a specific product.
     *
     * @param productId The UUID of the product.
     * @param message The notification message to be sent.
     */
    @Override
    public void sendNotification(UUID productId, String message) {
        // For now, we are just logging the notification. This can be extended to integrate with actual notification systems.
        logger.info("Sending notification for productId {}: {}", productId, message);
    }
}