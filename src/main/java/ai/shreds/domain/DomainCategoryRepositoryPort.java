package ai.shreds.domain;

import java.util.UUID;
import ai.shreds.domain.exceptions.CategoryNotFoundException;

/**
 * DomainCategoryRepositoryPort is an interface responsible for data access operations related to the Category entity.
 */
public interface DomainCategoryRepositoryPort {

    /**
     * Saves a new or updated category to the database.
     *
     * @param category the category entity to be saved
     */
    void save(DomainCategoryEntity category);

    /**
     * Finds a category by its ID.
     *
     * @param id the unique identifier of the category
     * @return the category entity
     * @throws CategoryNotFoundException if the category is not found
     */
    DomainCategoryEntity findById(UUID id) throws CategoryNotFoundException;

    /**
     * Deletes a category by its ID.
     *
     * @param id the unique identifier of the category
     * @throws CategoryNotFoundException if the category is not found
     */
    void deleteById(UUID id) throws CategoryNotFoundException;
}