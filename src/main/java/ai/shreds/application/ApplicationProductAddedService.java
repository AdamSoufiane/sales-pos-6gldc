package ai.shreds.application;

import ai.shreds.adapter.AdapterProductAddedRequestParams;
import ai.shreds.adapter.AdapterProductAddedResponseDTO;
import ai.shreds.application.ApplicationProductAddedException;
import ai.shreds.application.ApplicationProductAddedOutputPort;
import ai.shreds.application.ApplicationProductAddedInputPort;
import ai.shreds.domain.DomainProductAddedEvent;
import ai.shreds.domain.DomainProductAddedEventPort;
import ai.shreds.domain.DomainProductAddedEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ai.shreds.infrastructure.ProductClient;

@Service
@RequiredArgsConstructor
public class ApplicationProductAddedService implements ApplicationProductAddedInputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProductAddedService.class);
    private final ApplicationProductAddedOutputPort outputPort;
    private final DomainProductAddedEventPort domainProductAddedEventPort;
    private final ProductClient productClient;

    @Override
    @Transactional
    public AdapterProductAddedResponseDTO processProductAddedEvent(AdapterProductAddedRequestParams params) {
        try {
            // Validate event data
            validateEvent(params);

            // Map to domain event
            DomainProductAddedEvent domainEvent = mapToDomainEvent(params);

            // Process domain event
            DomainProductAddedEventResponse domainResponse = domainProductAddedEventPort.processEvent(domainEvent);

            // Map to adapter response
            return mapToAdapterResponse(domainResponse);
        } catch (ApplicationProductAddedException e) {
            logger.error("Validation error: {}", e.getMessage());
            return handleException(e);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return handleException(e);
        }
    }

    private void validateEvent(AdapterProductAddedRequestParams params) throws ApplicationProductAddedException {
        if (params.getProductId() == null || params.getProductId().isEmpty()) {
            throw new ApplicationProductAddedException("Invalid productId");
        }
        if (!productClient.checkProductExists(params.getProductId())) {
            throw new ApplicationProductAddedException("Product does not exist");
        }
        if (params.getInitialQuantity() <= 0) {
            throw new ApplicationProductAddedException("Initial quantity must be positive");
        }
        if (params.getAlertQuantity() > params.getInitialQuantity()) {
            throw new ApplicationProductAddedException("Alert quantity must be less than or equal to initial quantity");
        }
        if (params.getWarehouseLocation() == null || params.getWarehouseLocation().isEmpty()) {
            throw new ApplicationProductAddedException("Invalid warehouse location");
        }
    }

    private DomainProductAddedEvent mapToDomainEvent(AdapterProductAddedRequestParams params) {
        return new DomainProductAddedEvent(
                params.getProductId(),
                params.getInitialQuantity(),
                params.getCreationTime() == null ? LocalDateTime.now() : params.getCreationTime(),
                params.getAlertQuantity(),
                params.getWarehouseLocation()
        );
    }

    private AdapterProductAddedResponseDTO mapToAdapterResponse(DomainProductAddedEventResponse domainResponse) {
        return new AdapterProductAddedResponseDTO(domainResponse.getMessage());
    }

    private AdapterProductAddedResponseDTO handleException(Exception e) {
        return new AdapterProductAddedResponseDTO("Error: " + e.getMessage());
    }
}