package ai.shreds.domain;

import ai.shreds.shared.AdapterCategoryRequestParams;
import ai.shreds.shared.DomainCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainCategoryService {

    private final DomainCategoryRepositoryPort categoryRepository;

    /**
     * Fetches all categories from the repository.
     * @return List of DomainCategoryEntity objects.
     */
    public List<DomainCategoryEntity> getAllCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new DomainCategoryServiceException("Error retrieving all categories", e);
        }
    }

    /**
     * Retrieves a specific category by its unique identifier.
     * @param id Unique identifier of the category.
     * @return DomainCategoryEntity object.
     */
    public DomainCategoryEntity getCategoryById(Long id) {
        try {
            Optional<DomainCategoryEntity> category = categoryRepository.findById(id);
            category.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
            return category.get();
        } catch (Exception e) {
            throw new DomainCategoryServiceException("Error retrieving category by ID", e);
        }
    }

    /**
     * Searches for categories based on provided search criteria.
     * @param params AdapterCategoryRequestParams object containing search parameters.
     * @return Page of matching DomainCategoryEntity objects.
     */
    public Page<DomainCategoryEntity> searchCategories(AdapterCategoryRequestParams params) {
        try {
            Pageable pageable = PageRequest.of(params.getPage(), params.getSize());
            List<DomainCategoryEntity> result = categoryRepository.findByNameContaining(params.getName());
            if (result.isEmpty() && params.getCategoryId() != null) {
                result = categoryRepository.findByCategoryId(params.getCategoryId());
            }
            if (result.isEmpty() && params.getCreatedAfter() != null) {
                result = categoryRepository.findByCreatedAtAfter(params.getCreatedAfter());
            }
            if (result.isEmpty() && params.getUpdatedAfter() != null) {
                result = categoryRepository.findByUpdatedAtAfter(params.getUpdatedAfter());
            }
            return new PageImpl<>(result, pageable, result.size());
        } catch (Exception e) {
            throw new DomainCategoryServiceException("Error searching categories", e);
        }
    }

    public class DomainCategoryServiceException extends RuntimeException {
        public DomainCategoryServiceException(String message) {
            super(message);
        }

        public DomainCategoryServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }
}