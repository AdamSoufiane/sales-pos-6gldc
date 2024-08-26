package ai.shreds.application;

import ai.shreds.shared.SharedAlertDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the ApplicationAlertServiceOutputPort.
 * This service is responsible for sending alerts when inventory levels fall below a specified threshold.
 */
@Service
public class ApplicationAlertServiceImpl implements ApplicationAlertServiceOutputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationAlertServiceImpl.class);
    private final AlertServicePort alertServicePort;

    @Autowired
    public ApplicationAlertServiceImpl(AlertServicePort alertServicePort) {
        this.alertServicePort = alertServicePort;
    }

    /**
     * Sends an alert when inventory levels fall below the specified threshold.
     *
     * @param alert The alert details encapsulated in a SharedAlertDTO object.
     */
    @Override
    public void sendAlert(SharedAlertDTO alert) {
        String alertMessage = String.format("Alert: %s - Current Quantity: %d, Threshold: %d",
                alert.getAlertMessage(), alert.getCurrentQuantity(), alert.getThreshold());
        logger.info("Sending alert for productId {}: {}", alert.getProductId(), alertMessage);
        alertServicePort.sendNotification(alert.getProductId(), alertMessage);
    }

}