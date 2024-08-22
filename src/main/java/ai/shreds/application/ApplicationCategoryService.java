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
            UUID categoryId = request.getCategory_id() != null ? request.getCategory_id() : null;
            if (categoryId != null) {
                categoryService.checkParentCategoryExists(categoryId);
            }
            if (categoryRepository.existsByNameAndParentId(request.getName(), categoryId)) {
                throw new IllegalArgumentException("Category name must be unique within its parent category");
            }
            DomainCategoryEntity category = new DomainCategoryEntity();
            category.setId(UUID.randomUUID());
            category.setName(request.getName());
            category.setDescription(request.getDescription());
            category.setCategoryId(categoryId);
            category.setCreatedAt(LocalDateTime.now());
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);
            log.info("Category created successfully: {}", category.getId());
            return new AdapterCategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getCategoryId(),
                    category.getCreatedAt(),
                    category.getUpdatedAt()
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
            UUID categoryId = request.getCategory_id() != null ? request.getCategory_id() : null;
            UUID categoryID = id;
            DomainCategoryEntity existingCategory = categoryRepository.findById(categoryID);
            if (existingCategory == null) {
                throw new IllegalArgumentException("Category not found");
            }
            categoryService.validateCategoryData(request); // Changed to use the correct type AdapterCategoryUpdateRequest
            if (categoryId != null) {
                categoryService.checkParentCategoryExists(categoryId);
            }
            if (categoryRepository.existsByNameAndParentId(request.getName(), categoryId)) {
                throw new IllegalArgumentException("Category name must be unique within its parent category");
            }
            existingCategory.setName(request.getName());
            existingCategory.setDescription(request.getDescription());
            existingCategory.setCategoryId(categoryId);
            existingCategory.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(existingCategory);
            log.info("Category updated successfully: {}", categoryID);
            return new AdapterCategoryResponse(
                    existingCategory.getId(),
                    existingCategory.getName(),
                    existingCategory.getDescription(),
                    existingCategory.getCategoryId(),
                    existingCategory.getCreatedAt(),
                    existingCategory.getUpdatedAt()
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
            UUID categoryID = id;
            DomainCategoryEntity existingCategory = categoryRepository.findById(categoryID);
            if (existingCategory == null) {
                throw new IllegalArgumentException("Category not found");
            }
            if (categoryRepository.hasSubcategories(categoryID)) {
                throw new IllegalArgumentException("Category cannot be deleted as it has subcategories");
            }
            categoryRepository.deleteById(categoryID);
            log.info("Category deleted successfully: {}", categoryID);
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting category: {}", e.getMessage());
            throw new RuntimeException("Failed to delete category. Please ensure the category exists and has no subcategories.");
        }
    }
}