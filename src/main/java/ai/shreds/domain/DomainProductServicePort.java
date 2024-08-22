package ai.shreds.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface for product-related operations in the domain layer.
 */
public interface DomainProductServicePort {
    /**
     * Saves the given product entity to the database.
     *
     * @param product The product entity to save.
     */
    void save(DomainProductEntity product);

    /**
     * Finds a product entity by its unique identifier.
     *
     * @param id The unique identifier of the product.
     * @return An Optional containing the found product entity, or empty if not found.
     */
    Optional<DomainProductEntity> findById(UUID id);

    /**
     * Deletes a product entity by its unique identifier.
     *
     * @param id The unique identifier of the product to delete.
     */
    void deleteById(UUID id);
}