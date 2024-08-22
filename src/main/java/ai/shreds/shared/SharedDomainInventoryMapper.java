package ai.shreds.shared;

import ai.shreds.shared.SharedProductDomainEntity;
import ai.shreds.shared.SharedInventoryDomainEntity;
import ai.shreds.shared.dto.SharedAlertDTO;
import java.util.UUID;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class SharedDomainInventoryMapper {

    /**
     * Converts a SharedProductDomainEntity to a SharedInventoryDomainEntity.
     * @param productDomainEntity the product domain entity
     * @return the inventory domain entity
     */
    public SharedInventoryDomainEntity toInventoryDomainEntity(SharedProductDomainEntity productDomainEntity) {
        if (productDomainEntity == null) {
            return null;
        }
        SharedInventoryDomainEntity inventoryDomainEntity = new SharedInventoryDomainEntity();
        inventoryDomainEntity.setProductId(productDomainEntity.getProductId());
        inventoryDomainEntity.setQuantity(0); // Default quantity
        inventoryDomainEntity.setQteAlert(10); // Default alert threshold
        inventoryDomainEntity.setLastUpdated(productDomainEntity.getUpdatedAt());
        return inventoryDomainEntity;
    }

    /**
     * Converts a SharedProductDomainEntity and a SharedInventoryDomainEntity to a SharedAlertDTO.
     * @param productDomainEntity the product domain entity
     * @param inventoryDomainEntity the inventory domain entity
     * @return the alert DTO
     */
    public SharedAlertDTO toAlertDTO(SharedProductDomainEntity productDomainEntity, SharedInventoryDomainEntity inventoryDomainEntity) {
        if (productDomainEntity == null || inventoryDomainEntity == null) {
            return null;
        }
        SharedAlertDTO alertDTO = new SharedAlertDTO();
        alertDTO.setProductId(productDomainEntity.getProductId());
        alertDTO.setAlertMessage("Inventory level is below threshold!"); // Example alert message
        alertDTO.setThreshold(inventoryDomainEntity.getQteAlert());
        alertDTO.setCurrentQuantity(inventoryDomainEntity.getQuantity());
        return alertDTO;
    }
}