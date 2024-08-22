package ai.shreds.domain;

import ai.shreds.shared.SharedAlertDTO;

/**
 * Domain port for triggering alerts when inventory levels fall below a specified threshold.
 */
public interface DomainAlertServicePort {
    /**
     * Triggers an alert based on the provided alert details.
     *
     * @param alert The alert details to be triggered. Must not be null.
     */
    void triggerAlert(SharedAlertDTO alert);
}