package ai.shreds.domain;

import ai.shreds.shared.dto.SharedAlertDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of the DomainAlertServicePort interface.
 * This class is responsible for triggering alerts when inventory levels fall below a specified threshold.
 */
@Service
public class DomainAlertServiceImpl implements DomainAlertServicePort {

    private static final Logger log = LoggerFactory.getLogger(DomainAlertServiceImpl.class);

    /**
     * Triggers an alert when inventory levels fall below a specified threshold.
     *
     * @param alert the alert details including product ID, alert message, threshold, and current quantity.
     */
    @Override
    public void triggerAlert(SharedAlertDTO alert) {
        try {
            log.info("Triggering alert for product ID: {}", alert.getProductId());
            sendAlert(alert);
        } catch (Exception e) {
            log.error("Error while triggering alert for product ID: {}", alert.getProductId(), e);
            throw new AlertTriggerException("Failed to trigger alert for product ID: " + alert.getProductId(), e);
        }
    }

    private void sendAlert(SharedAlertDTO alert) {
        // Implementation for sending alert
        // This could involve calling another service or sending a notification
        log.info("Alert sent: {}", alert.getAlertMessage());
    }

    private static class AlertTriggerException extends RuntimeException {
        public AlertTriggerException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}