package ai.shreds.domain;

import ai.shreds.adapter.AdapterCategoryCreateRequest;
import ai.shreds.application.ApplicationCategoryException;
import ai.shreds.shared.SharedUUID;
import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Validated
@Service
public class DomainCategoryService implements DomainCategoryServicePort {
    private static final Logger log = LoggerFactory.getLogger(DomainCategoryService.class);
    private final DomainCategoryRepositoryPort categoryRepository;

    @Autowired
    public DomainCategoryService(DomainCategoryRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void validateCategoryData(@NotNull @Size(min = 1, max = 100) AdapterCategoryCreateRequest request) {
        try {
            if (request.getName() == null || request.getName().isEmpty()) {
                throw new ApplicationCategoryException("Category name is required.");
            }

            if (request.getCategory_id() != null) {
                SharedUUID parentId = new SharedUUID(request.getCategory_id().toString());
                checkParentCategoryExists(parentId);
            }

            if (!isCategoryNameUniqueWithinParent(request.getName(), new SharedUUID(request.getCategory_id().toString()))) {
                throw new ApplicationCategoryException("Category name must be unique within its parent category.");
            }
        } catch (Exception e) {
            log.error("An error occurred during category validation: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void checkParentCategoryExists(@NotNull SharedUUID id) {
        DomainCategoryEntity parentCategory = categoryRepository.findById(id.getValue());
        if (parentCategory == null) {
            throw new ApplicationCategoryException("Parent category does not exist.");
        }
    }

    private boolean isCategoryNameUniqueWithinParent(String name, @NotNull SharedUUID parentId) {
        return categoryRepository.findByNameAndParentId(name, parentId.getValue()) == null;
    }

    @Transactional
    private void validateCategoryDeletion(@NotNull SharedUUID id) {
        try {
            if (!categoryRepository.findSubcategoriesByParentId(id.getValue()).isEmpty()) {
                throw new ApplicationCategoryException("Category cannot be deleted as it has subcategories.");
            }
        } catch (Exception e) {
            log.error("An error occurred during category deletion validation: " + e.getMessage());
            throw e;
        }
    }
}