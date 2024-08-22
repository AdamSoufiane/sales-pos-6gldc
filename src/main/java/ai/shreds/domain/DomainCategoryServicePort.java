package ai.shreds.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * DomainCategoryServicePort defines the contract for category-related operations.
 */
public interface DomainCategoryServicePort {
    /**
     * Finds a category by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return an Optional containing the found category or empty if not found
     */
    Optional<DomainCategoryEntity> findById(UUID id);

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories
     */
    List<DomainCategoryEntity> findAll();
}