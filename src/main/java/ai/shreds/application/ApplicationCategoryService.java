package ai.shreds.application;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryServicePort;
import ai.shreds.shared.AdapterCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for handling category-related operations.
 */
@Service
public class ApplicationCategoryService implements ApplicationCategoryServicePort {

    private final DomainCategoryServicePort domainCategoryService;

    @Autowired
    public ApplicationCategoryService(DomainCategoryServicePort domainCategoryService) {
        this.domainCategoryService = domainCategoryService;
    }

    /**
     * Fetches category details by ID.
     *
     * @param id the UUID of the category
     * @return the category details as AdapterCategoryDTO
     */
    @Override
    public AdapterCategoryDTO getCategoryById(UUID id) {
        Optional<DomainCategoryEntity> categoryEntityOptional = domainCategoryService.findById(id);
        if (categoryEntityOptional.isPresent()) {
            DomainCategoryEntity categoryEntity = categoryEntityOptional.get();
            return categoryEntity.toAdapterCategoryDTO();
        } else {
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }
    }

    private static class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }
}