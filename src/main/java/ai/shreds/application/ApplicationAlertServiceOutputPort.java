package ai.shreds.application;

import ai.shreds.shared.dto.SharedAlertDTO;

public interface ApplicationAlertServiceOutputPort {
    void sendAlert(SharedAlertDTO alert);
}