package ai.shreds.domain;

import ai.shreds.adapter.AdapterCategoryCreateRequest;
import java.util.UUID;

/**
 * DomainCategoryServicePort defines the contract for the service layer responsible for handling core business logic related to category management.
 * It includes methods for validating category data and checking if a parent category exists.
 */
public interface DomainCategoryServicePort {
    /**
     * Validates the input data for creating or updating a category.
     * @param request the category creation request containing the details of the category to be created.
     */
    void validateCategoryData(AdapterCategoryCreateRequest request);

    /**
     * Checks if the parent category exists when a new category is being created or updated.
     * @param id the UUID of the parent category.
     */
    void checkParentCategoryExists(UUID id);
}