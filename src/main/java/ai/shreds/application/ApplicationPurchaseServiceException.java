package ai.shreds.application;

import lombok.extern.slf4j.Slf4j;
import ai.shreds.adapter.AdapterPurchaseResponseDTO;
import ai.shreds.adapter.dto.AdapterSharedErrorResponse;
import org.springframework.stereotype.Component;

/**
 * This class handles exceptions that occur within the ApplicationPurchaseService.
 */
@Slf4j
@Component
public class ApplicationPurchaseServiceException {

    /**
     * Handles exceptions and returns an appropriate AdapterPurchaseResponseDTO object.
     * 
     * @param e the exception that occurred
     * @return AdapterPurchaseResponseDTO containing error details
     */
    public AdapterPurchaseResponseDTO handleException(Exception e) {
        log.error("An internal server error occurred while processing the purchase transaction: {}", e.getMessage(), e);
        AdapterSharedErrorResponse errorResponse = new AdapterSharedErrorResponse();
        errorResponse.setStatus_code(500);
        errorResponse.setError("Internal server error: " + e.getMessage());

        AdapterPurchaseResponseDTO response = new AdapterPurchaseResponseDTO();
        response.setStatus_code(errorResponse.getStatus_code());
        response.setData(null); // AdapterSharedErrorResponse's data is null by definition
        response.setError(errorResponse.getError());
        return response;
    }
}