package ai.shreds.domain;

import ai.shreds.shared.dto.SharedAlertDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

/**
 * Service responsible for sending alerts when inventory levels fall below a specified threshold.
 */
@RequiredArgsConstructor
public class DomainAlertService implements DomainAlertServicePort {
    private static final Logger logger = LoggerFactory.getLogger(DomainAlertService.class);
    private final DomainAlertServicePort alertService;

    /**
     * Sends an alert using the provided alert service.
     *
     * @param alert the alert details to be sent
     */
    @Override
    public void triggerAlert(SharedAlertDTO alert) {
        try {
            alertService.triggerAlert(alert);
            logger.info("Alert sent successfully for productId: {}", alert.getProductId());
        } catch (Exception e) {
            logger.error("Failed to send alert for productId: {}", alert.getProductId(), e);
            throw new RuntimeException("Failed to send alert", e);
        }
    }
}