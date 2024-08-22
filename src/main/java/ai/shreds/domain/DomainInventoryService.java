package ai.shreds.domain;

import ai.shreds.shared.SharedInventoryDomainEntity;
import ai.shreds.shared.SharedProductDomainEntity;
import ai.shreds.shared.dto.SharedAlertDTO;
import ai.shreds.infrastructure.InfrastructureProductRepositoryPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;

@Service
@RequiredArgsConstructor
public class DomainInventoryService {

    private static final Logger logger = LoggerFactory.getLogger(DomainInventoryService.class);

    private final DomainInventoryRepositoryPort inventoryRepository;
    private final DomainAlertServicePort alertService;
    private final InfrastructureProductRepositoryPort productRepository;

    @Transactional
    public void initializeOrUpdateInventory(SharedProductDomainEntity product) {
        if (productRepository.findById(product.getProductId()) == null) {
            throw new ProductNotFoundException("Product does not exist");
        }
        SharedInventoryDomainEntity inventory = inventoryRepository.findByProductId(product.getProductId());
        if (inventory == null) {
            inventory = new SharedInventoryDomainEntity();
            inventory.setProductId(product.getProductId());
            inventory.setQuantity(0); // Initial quantity can be set to 0 or any default value
            inventory.setQteAlert(10); // Default alert threshold, can be customized
        }
        inventory.setLastUpdated(product.getUpdatedAt());
        try {
            inventoryRepository.save(inventory);
            logger.info("Initialized or updated inventory for product: {}", product.getProductId());
            triggerAlertIfNeeded(inventory);
        } catch (OptimisticLockingFailureException e) {
            handleOptimisticLockingFailure(e);
        }
    }

    @Transactional
    public void adjustInventoryForUpdatedProduct(SharedProductDomainEntity product) {
        if (productRepository.findById(product.getProductId()) == null) {
            throw new ProductNotFoundException("Product does not exist");
        }
        SharedInventoryDomainEntity inventory = inventoryRepository.findByProductId(product.getProductId());
        if (inventory != null) {
            inventory.setLastUpdated(product.getUpdatedAt());
            try {
                inventoryRepository.save(inventory);
                logger.info("Adjusted inventory for updated product: {}", product.getProductId());
                triggerAlertIfNeeded(inventory);
            } catch (OptimisticLockingFailureException e) {
                handleOptimisticLockingFailure(e);
            }
        }
    }

    @Transactional
    public void removeInventoryForDeletedProduct(UUID productId) {
        if (productRepository.findById(productId) == null) {
            throw new ProductNotFoundException("Product does not exist");
        }
        inventoryRepository.deleteByProductId(productId);
        logger.info("Removed inventory for deleted product: {}", productId);
    }

    public void triggerAlertIfNeeded(SharedInventoryDomainEntity inventory) {
        if (inventory.getQuantity() < inventory.getQteAlert()) {
            SharedAlertDTO alert = new SharedAlertDTO();
            alert.setProductId(inventory.getProductId());
            alert.setAlertMessage("Inventory level below threshold");
            alert.setThreshold(inventory.getQteAlert());
            alert.setCurrentQuantity(inventory.getQuantity());
            alertService.triggerAlert(alert);
            logger.warn("Triggered alert for product: {} due to low inventory", inventory.getProductId());
        }
    }

    public void processProductAddedEvent(SharedProductDomainEntity product) {
        initializeOrUpdateInventory(product);
    }

    public void processProductUpdatedEvent(SharedProductDomainEntity product) {
        adjustInventoryForUpdatedProduct(product);
    }

    public void processProductDeletedEvent(SharedProductDomainEntity product) {
        removeInventoryForDeletedProduct(product.getProductId());
    }

    public void triggerAlertIfNecessary(SharedProductDomainEntity product) {
        SharedInventoryDomainEntity inventory = inventoryRepository.findByProductId(product.getProductId());
        if (inventory != null) {
            triggerAlertIfNeeded(inventory);
        }
    }

    @Transactional
    private void handleOptimisticLockingFailure(OptimisticLockingFailureException e) {
        logger.error("Optimistic locking failure: {}", e.getMessage());
        throw e;
    }

    class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}