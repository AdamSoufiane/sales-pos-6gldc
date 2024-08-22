package ai.shreds.application;

import ai.shreds.shared.SharedAlertDTO;

public interface ApplicationAlertServiceOutputPort {
    void sendAlert(SharedAlertDTO alert);
}