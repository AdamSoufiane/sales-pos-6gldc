package ai.shreds.application;

import ai.shreds.adapter.AdapterCategoryCreateRequest;
import ai.shreds.adapter.AdapterCategoryUpdateRequest;
import ai.shreds.adapter.AdapterCategoryResponse;
import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import ai.shreds.domain.DomainCategoryServicePort;
import ai.shreds.shared.SharedUUID;
import ai.shreds.shared.SharedTimestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class ApplicationCategoryService implements ApplicationCreateCategoryInputPort, ApplicationUpdateCategoryInputPort, ApplicationDeleteCategoryInputPort {

    private final DomainCategoryRepositoryPort categoryRepository;
    private final DomainCategoryServicePort categoryService;

    @Autowired
    public ApplicationCategoryService(DomainCategoryRepositoryPort categoryRepository, DomainCategoryServicePort categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public AdapterCategoryResponse createCategory(@Valid AdapterCategoryCreateRequest request) {
        try {
            categoryService.validateCategoryData(request);
            SharedUUID categoryId = request.getCategory_id() != null ? new SharedUUID(request.getCategory_id().toString()) : null;
            if (categoryId != null) {
                categoryService.checkParentCategoryExists(categoryId.getValue());
            }
            if (categoryRepository.existsByNameAndParentId(request.getName(), categoryId)) {
                throw new IllegalArgumentException("Category name must be unique within its parent category");
            }
            DomainCategoryEntity category = new DomainCategoryEntity();
            category.setId(new SharedUUID(UUID.randomUUID().toString()));
            category.setName(request.getName());
            category.setDescription(request.getDescription());
            category.setCategory_id(categoryId);
            category.setCreated_at(new SharedTimestamp(LocalDateTime.now()));
            category.setUpdated_at(new SharedTimestamp(LocalDateTime.now()));
            categoryRepository.save(category);
            log.info("Category created successfully: {}", category.getId().getValue());
            return new AdapterCategoryResponse(
                    UUID.fromString(category.getId().getValue()),
                    category.getName(),
                    category.getDescription(),
                    category.getCategory_id() != null ? UUID.fromString(category.getCategory_id().getValue()) : null,
                    category.getCreated_at().getValue(),
                    category.getUpdated_at().getValue()
            );
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating category: {}", e.getMessage());
            throw new RuntimeException("Failed to create category. Please check your input data.");
        }
    }

    @Override
    @Transactional
    public AdapterCategoryResponse updateCategory(UUID id, @Valid AdapterCategoryUpdateRequest request) {
        try {
            SharedUUID categoryId = request.getCategory_id() != null ? new SharedUUID(request.getCategory_id().toString()) : null;
            SharedUUID categoryID = new SharedUUID(id.toString());
            DomainCategoryEntity existingCategory = categoryRepository.findById(categoryID.getValue());
            if (existingCategory == null) {
                throw new IllegalArgumentException("Category not found");
            }
            categoryService.validateCategoryData(request);
            if (categoryId != null) {
                categoryService.checkParentCategoryExists(categoryId.getValue());
            }
            if (categoryRepository.existsByNameAndParentId(request.getName(), categoryId)) {
                throw new IllegalArgumentException("Category name must be unique within its parent category");
            }
            existingCategory.setName(request.getName());
            existingCategory.setDescription(request.getDescription());
            existingCategory.setCategory_id(categoryId);
            existingCategory.setUpdated_at(new SharedTimestamp(LocalDateTime.now()));
            categoryRepository.save(existingCategory);
            log.info("Category updated successfully: {}", categoryID.getValue());
            return new AdapterCategoryResponse(
                    UUID.fromString(categoryID.getValue()),
                    existingCategory.getName(),
                    existingCategory.getDescription(),
                    existingCategory.getCategory_id() != null ? UUID.fromString(existingCategory.getCategory_id().getValue()) : null,
                    existingCategory.getCreated_at().getValue(),
                    existingCategory.getUpdated_at().getValue()
            );
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating category: {}", e.getMessage());
            throw new RuntimeException("Failed to update category. Please check your input data.");
        }
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        try {
            SharedUUID categoryID = new SharedUUID(id.toString());
            DomainCategoryEntity existingCategory = categoryRepository.findById(categoryID.getValue());
            if (existingCategory == null) {
                throw new IllegalArgumentException("Category not found");
            }
            if (categoryRepository.hasSubcategories(categoryID)) {
                throw new IllegalArgumentException("Category cannot be deleted as it has subcategories");
            }
            categoryRepository.deleteById(categoryID.getValue());
            log.info("Category deleted successfully: {}", categoryID.getValue());
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting category: {}", e.getMessage());
            throw new RuntimeException("Failed to delete category. Please ensure the category exists and has no subcategories.");
        }
    }
}