package ai.shreds.domain;

import java.util.List;
import java.util.UUID;

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
     */
    DomainCategoryEntity findById(UUID id);

    /**
     * Deletes a category by its ID.
     *
     * @param id the unique identifier of the category
     */
    void deleteById(UUID id);

    /**
     * Checks if a category with the given name and parent ID exists.
     *
     * @param name the name of the category
     * @param parentId the unique identifier of the parent category
     * @return true if a category with the given name and parent ID exists, false otherwise
     */
    boolean existsByNameAndParentId(String name, UUID parentId);

    /**
     * Checks if a category has subcategories.
     *
     * @param parentId the unique identifier of the parent category
     * @return true if the category has subcategories, false otherwise
     */
    boolean hasSubcategories(UUID parentId);

    /**
     * Finds a category by its name and parent ID.
     *
     * @param name the name of the category
     * @param parentId the unique identifier of the parent category
     * @return the category entity
     */
    DomainCategoryEntity findByNameAndParentId(String name, UUID parentId);

    /**
     * Finds subcategories by the parent ID.
     *
     * @param parentId the unique identifier of the parent category
     * @return a list of subcategory entities
     */
    List<DomainCategoryEntity> findSubcategoriesByParentId(UUID parentId);
}