package ai.shreds.domain;

import ai.shreds.domain.DomainInventoryEntity;

/**
 * Interface for Inventory Repository operations in the Domain layer.
 * This interface provides methods to save and retrieve inventory records.
 */
public interface DomainInventoryRepositoryPort {
    /**
     * Saves a new inventory record or updates an existing one.
     *
     * @param entity the inventory entity to be saved or updated
     */
    void save(DomainInventoryEntity entity);

    /**
     * Finds an inventory record by the product ID.
     *
     * @param productId the unique identifier of the product
     * @return the inventory entity associated with the given product ID
     */
    DomainInventoryEntity findByProductId(String productId);
}