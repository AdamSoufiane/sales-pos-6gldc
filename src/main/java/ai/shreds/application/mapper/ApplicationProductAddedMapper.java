package ai.shreds.application.mapper;

import ai.shreds.adapter.AdapterProductAddedRequestParams;
import ai.shreds.adapter.AdapterProductAddedResponseDTO;
import ai.shreds.domain.DomainProductAddedEvent;
import ai.shreds.domain.DomainProductAddedEventResponse;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationProductAddedMapper {

    public DomainProductAddedEvent mapToDomainEvent(@NonNull AdapterProductAddedRequestParams params) {
        // Validate input parameters
        if (params.getProductId() == null || params.getProductId().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        if (params.getInitialQuantity() <= 0) {
            throw new IllegalArgumentException("Initial quantity must be a positive integer");
        }
        if (params.getAlertQuantity() > params.getInitialQuantity()) {
            throw new IllegalArgumentException("Alert quantity must be less than or equal to initial quantity");
        }
        if (params.getWarehouseLocation() == null || params.getWarehouseLocation().isEmpty()) {
            throw new IllegalArgumentException("Warehouse location cannot be null or empty");
        }

        if (params.getCreationTime() == null) {
            throw new IllegalArgumentException("Creation time cannot be null");
        }

        DomainProductAddedEvent domainEvent = new DomainProductAddedEvent();
        domainEvent.setProductId(params.getProductId());
        domainEvent.setInitialQuantity(params.getInitialQuantity());
        domainEvent.setCreationTime(params.getCreationTime());
        domainEvent.setAlertQuantity(params.getAlertQuantity());
        domainEvent.setWarehouseLocation(params.getWarehouseLocation());
        return domainEvent;
    }

    public AdapterProductAddedResponseDTO mapToAdapterResponse(@Nullable DomainProductAddedEventResponse domainResponse) {
        // Handle possible null values
        if (domainResponse == null || domainResponse.getMessage() == null) {
            throw new IllegalArgumentException("Domain response or message cannot be null");
        }

        return new AdapterProductAddedResponseDTO(domainResponse.getMessage());
    }
}