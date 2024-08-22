package ai.shreds.application;

import ai.shreds.shared.AdapterCategoryDTO;
import java.util.UUID;

/**
 * ApplicationCategoryServicePort defines the contract for category-related operations
 * that the application layer will implement.
 */
public interface ApplicationCategoryServicePort {
    /**
     * Fetches category details by ID from the Category Service.
     *
     * @param id the UUID of the category
     * @return AdapterCategoryDTO containing category details
     */
    AdapterCategoryDTO getCategoryById(UUID id);
}