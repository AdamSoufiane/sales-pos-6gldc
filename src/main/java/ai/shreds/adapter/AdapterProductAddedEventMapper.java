package ai.shreds.adapter;

import ai.shreds.adapter.AdapterProductAddedRequestParams;
import ai.shreds.adapter.AdapterProductAddedResponseDTO;
import ai.shreds.domain.DomainProductAddedEvent;
import ai.shreds.domain.DomainProductAddedEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdapterProductAddedEventMapper {
    private static final Logger logger = LoggerFactory.getLogger(AdapterProductAddedEventMapper.class);

    public DomainProductAddedEvent mapToDomainEvent(AdapterProductAddedRequestParams params) {
        if (params == null || params.getProductId() == null || params.getProductId().isEmpty() ||
            params.getInitialQuantity() == null || params.getCreationTime() == null ||
            params.getAlertQuantity() == null || params.getWarehouseLocation() == null ||
            params.getWarehouseLocation().isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        logger.info("Mapping AdapterProductAddedRequestParams to DomainProductAddedEvent");
        return DomainProductAddedEvent.builder()
                .productId(params.getProductId())
                .initialQuantity(params.getInitialQuantity())
                .creationTime(params.getCreationTime())
                .alertQuantity(params.getAlertQuantity())
                .warehouseLocation(params.getWarehouseLocation())
                .build();
    }

    public AdapterProductAddedResponseDTO mapToAdapterResponse(DomainProductAddedEventResponse response) {
        if (response == null || response.getMessage() == null || response.getMessage().isEmpty()) {
            throw new IllegalArgumentException("Invalid response");
        }

        logger.info("Mapping DomainProductAddedEventResponse to AdapterProductAddedResponseDTO");
        return AdapterProductAddedResponseDTO.builder()
                .message(response.getMessage())
                .build();
    }
}