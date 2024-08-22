package ai.shreds.application;

import ai.shreds.domain.DomainInventoryRepositoryPort;
import ai.shreds.shared.SharedAlertDTO;
import ai.shreds.shared.SharedProductAddedEventDTO;
import ai.shreds.shared.SharedProductDeletedEventDTO;
import ai.shreds.shared.SharedProductUpdatedEventDTO;
import ai.shreds.shared.SharedDomainInventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationInventoryServiceImpl implements ApplicationInventoryServiceInputPort {

    private final DomainInventoryRepositoryPort domainInventoryRepositoryPort;
    private final ApplicationAlertServiceOutputPort applicationAlertServiceOutputPort;
    private final SharedDomainInventoryMapper sharedDomainInventoryMapper;

    @Override
    @Transactional
    public void processProductAddedEvent(SharedProductAddedEventDTO event) {
        try {
            log.info("Processing ProductAdded event: {}", event);
            // Convert DTO to domain entity
            var inventoryDomainEntity = sharedDomainInventoryMapper.toInventoryDomainEntity(event);

            // Initialize or update inventory
            domainInventoryRepositoryPort.save(inventoryDomainEntity);

            // Check and trigger alert if needed
            checkAndTriggerAlert(inventoryDomainEntity.getProductId());
        } catch (Exception e) {
            log.error("Error processing ProductAdded event: {}", event, e);
        }
    }

    @Override
    @Transactional
    public void processProductUpdatedEvent(SharedProductUpdatedEventDTO event) {
        try {
            log.info("Processing ProductUpdated event: {}", event);
            // Convert DTO to domain entity
            var inventoryDomainEntity = sharedDomainInventoryMapper.toInventoryDomainEntity(event);

            // Adjust inventory for updated product
            domainInventoryRepositoryPort.save(inventoryDomainEntity);

            // Check and trigger alert if needed
            checkAndTriggerAlert(inventoryDomainEntity.getProductId());
        } catch (Exception e) {
            log.error("Error processing ProductUpdated event: {}", event, e);
        }
    }

    @Override
    @Transactional
    public void processProductDeletedEvent(SharedProductDeletedEventDTO event) {
        try {
            log.info("Processing ProductDeleted event: {}", event);
            // Remove inventory for deleted product
            domainInventoryRepositoryPort.deleteByProductId(event.getProductId());
        } catch (Exception e) {
            log.error("Error processing ProductDeleted event: {}", event, e);
        }
    }

    private void checkAndTriggerAlert(UUID productId) {
        try {
            var inventory = domainInventoryRepositoryPort.findByProductId(productId);
            if (inventory.getQuantity() < inventory.getQteAlert()) {
                var alert = sharedDomainInventoryMapper.toAlertDTO(inventory);
                applicationAlertServiceOutputPort.sendAlert(alert);
            }
        } catch (Exception e) {
            log.error("Error checking and triggering alert for productId: {}", productId, e);
        }
    }
}