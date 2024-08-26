package ai.shreds.adapter;

import ai.shreds.domain.DomainProductAddedEvent;
import ai.shreds.domain.DomainProductAddedEventResponse;
import ai.shreds.adapter.AdapterProductAddedRequestParams;
import ai.shreds.adapter.AdapterProductAddedResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        DomainProductAddedEvent domainEvent = new DomainProductAddedEvent();
        domainEvent.setProductId(params.getProductId());
        domainEvent.setInitialQuantity(params.getInitialQuantity());
        domainEvent.setCreationTime(params.getCreationTime());
        domainEvent.setAlertQuantity(params.getAlertQuantity());
        domainEvent.setWarehouseLocation(params.getWarehouseLocation());
        return domainEvent;
    }

    public AdapterProductAddedResponseDTO mapToAdapterResponse(DomainProductAddedEventResponse response) {
        if (response == null || response.getMessage() == null || response.getMessage().isEmpty()) {
            throw new IllegalArgumentException("Invalid response");
        }

        logger.info("Mapping DomainProductAddedEventResponse to AdapterProductAddedResponseDTO");
        return new AdapterProductAddedResponseDTO(response.getMessage());
    }
}