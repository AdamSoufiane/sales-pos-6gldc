package ai.shreds.application;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryServicePort;
import ai.shreds.adapter.AdapterCategoryDTO;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationCategoryServiceImpl implements ApplicationCategoryServicePort {

    private final DomainCategoryServicePort domainCategoryService;

    @Override
    public AdapterCategoryDTO getCategoryById(UUID id) {
        return domainCategoryService.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("Category not found"))
            .toAdapterCategoryDTO();
    }
}

class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}