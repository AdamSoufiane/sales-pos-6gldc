package ai.shreds.application.mapper;

import ai.shreds.adapter.dto.AdapterProductAddedRequestParams;
import ai.shreds.adapter.dto.AdapterProductAddedResponseDTO;
import ai.shreds.domain.entity.DomainProductAddedEvent;
import ai.shreds.domain.entity.DomainProductAddedEventResponse;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationProductAddedMapper {

    public DomainProductAddedEvent mapToDomainEntity(@NonNull AdapterProductAddedRequestParams params) {
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

        return DomainProductAddedEvent.builder()
                .productId(params.getProductId())
                .initialQuantity(params.getInitialQuantity())
                .creationTime(params.getCreationTime())
                .alertQuantity(params.getAlertQuantity())
                .warehouseLocation(params.getWarehouseLocation())
                .build();
    }

    public AdapterProductAddedResponseDTO mapToAdapterResponse(@Nullable DomainProductAddedEventResponse domainResponse) {
        // Handle possible null values
        if (domainResponse == null || domainResponse.getMessage() == null) {
            throw new IllegalArgumentException("Domain response or message cannot be null");
        }

        return AdapterProductAddedResponseDTO.builder()
                .message(domainResponse.getMessage())
                .build();
    }
}