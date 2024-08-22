package ai.shreds.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DomainCategoryRepositoryPort {
    /**
     * Finds a category entity by its ID.
     *
     * @param id the UUID of the category to find
     * @return an Optional containing the found DomainCategoryEntity, or empty if not found
     */
    Optional<DomainCategoryEntity> findById(UUID id);

    /**
     * Finds all category entities.
     *
     * @return a list of all DomainCategoryEntity instances
     */
    List<DomainCategoryEntity> findAll();
}