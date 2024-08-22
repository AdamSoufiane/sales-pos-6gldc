package ai.shreds.application;

import ai.shreds.adapter.AdapterProductCreateRequest;
import ai.shreds.adapter.AdapterProductCreateResponse;
import ai.shreds.adapter.AdapterProductUpdateRequest;
import ai.shreds.adapter.AdapterProductUpdateResponse;
import ai.shreds.adapter.AdapterProductDeleteResponse;
import java.util.UUID;

/**
 * ApplicationProductServicePort defines the contract for product service operations within the application layer.
 */
public interface ApplicationProductServicePort {

    /**
     * Creates a new product.
     *
     * @param params the product creation request containing product details
     * @return the response containing created product details
     * @throws IllegalArgumentException if the product data is invalid
     */
    AdapterProductCreateResponse createProduct(AdapterProductCreateRequest params) throws IllegalArgumentException;

    /**
     * Updates an existing product.
     *
     * @param id the UUID of the product to be updated
     * @param params the product update request containing updated product details
     * @return the response containing updated product details
     * @throws IllegalArgumentException if the product data is invalid
     */
    AdapterProductUpdateResponse updateProduct(UUID id, AdapterProductUpdateRequest params) throws IllegalArgumentException;

    /**
     * Deletes a product by its ID.
     *
     * @param id the UUID of the product to be deleted
     * @return the response indicating the result of the deletion operation
     */
    AdapterProductDeleteResponse deleteProduct(UUID id);
}