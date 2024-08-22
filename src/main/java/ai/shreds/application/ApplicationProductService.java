package ai.shreds.application;

import ai.shreds.adapter.AdapterCategoryDTO;
import ai.shreds.adapter.AdapterProductCreateRequest;
import ai.shreds.adapter.AdapterProductCreateResponse;
import ai.shreds.adapter.AdapterProductDeleteResponse;
import ai.shreds.adapter.AdapterProductUpdateRequest;
import ai.shreds.adapter.AdapterProductUpdateResponse;
import ai.shreds.adapter.AdapterKafkaProducer;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductServicePort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing products.
 */
@Service
public class ApplicationProductService implements ApplicationProductServicePort {

    private final ApplicationCategoryServicePort applicationCategoryService;
    private final DomainProductServicePort domainProductService;
    private final AdapterKafkaProducer kafkaProducer;

    public ApplicationProductService(ApplicationCategoryServicePort applicationCategoryService, DomainProductServicePort domainProductService, AdapterKafkaProducer kafkaProducer) {
        this.applicationCategoryService = applicationCategoryService;
        this.domainProductService = domainProductService;
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * Creates a new product.
     *
     * @param params the product creation request parameters
     * @return the created product response
     */
    @Override
    public AdapterProductCreateResponse createProduct(AdapterProductCreateRequest params) {
        // Validate product data
        validateProductData(params);

        // Fetch category details
        AdapterCategoryDTO category = applicationCategoryService.getCategoryById(params.getCategoryId());
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        // Create DomainProductEntity
        DomainProductEntity product = new DomainProductEntity(UUID.randomUUID(), params.getName(), params.getDescription(), params.getPrice(), params.getCategoryId(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        // Save product entity
        domainProductService.save(product);

        // Publish ProductAdded event
        kafkaProducer.produceEvent("ProductAdded", product);

        // Return response
        return product.toAdapterProductCreateResponse(category);
    }

    /**
     * Updates an existing product.
     *
     * @param id the product ID
     * @param params the product update request parameters
     * @return the updated product response
     */
    @Override
    public AdapterProductUpdateResponse updateProduct(UUID id, AdapterProductUpdateRequest params) {
        // Validate product data
        validateProductData(params);

        // Fetch existing product
        Optional<DomainProductEntity> existingProductOpt = domainProductService.findById(id);
        if (!existingProductOpt.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        DomainProductEntity existingProduct = existingProductOpt.get();

        // Ensure product category is fetched with the existing product category ID
        AdapterCategoryDTO category = applicationCategoryService.getCategoryById(existingProduct.getCategoryId());
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        // Update product entity
        existingProduct.setName(params.getName());
        existingProduct.setDescription(params.getDescription());
        existingProduct.setPrice(params.getPrice());
        existingProduct.setCategoryId(params.getCategoryId());
        existingProduct.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // Save updated product entity
        domainProductService.save(existingProduct);

        // Publish ProductUpdated event
        kafkaProducer.produceEvent("ProductUpdated", existingProduct);

        // Return response
        return existingProduct.toAdapterProductUpdateResponse(category);
    }

    /**
     * Deletes a product.
     *
     * @param id the product ID
     * @return the delete product response
     */
    @Override
    public AdapterProductDeleteResponse deleteProduct(UUID id) {
        // Find existing product
        Optional<DomainProductEntity> existingProductOpt = domainProductService.findById(id);
        if (!existingProductOpt.isPresent()) {
            throw new RuntimeException("Product not found");
        }

        // Delete product entity
        DomainProductEntity existingProduct = existingProductOpt.get();
        domainProductService.deleteById(id);

        // Publish ProductDeleted event
        kafkaProducer.produceEvent("ProductDeleted", existingProduct);

        // Return response
        return new AdapterProductDeleteResponse();
    }

    /**
     * Utility method to validate product data.
     */
    private void validateProductData(AdapterProductCreateRequest params) {
        if (params.getName() == null || params.getName().isEmpty()) {
            throw new RuntimeException("Product name must not be empty");
        }
        if (params.getPrice() == null || params.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Product price must be a positive value");
        }
        if (params.getCategoryId() == null) {
            throw new RuntimeException("Category ID must not be null");
        }
    }

    /**
     * Utility method to validate product data.
     */
    private void validateProductData(AdapterProductUpdateRequest params) {
        validateProductData(new AdapterProductCreateRequest(params.getName(), params.getDescription(), params.getPrice(), params.getCategoryId()));
    }
}