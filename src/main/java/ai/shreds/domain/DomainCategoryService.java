package ai.shreds.domain;

import ai.shreds.adapter.AdapterCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainCategoryService implements DomainCategoryServicePort {
    private static final Logger logger = LoggerFactory.getLogger(DomainCategoryService.class);
    private final DomainCategoryRepositoryPort categoryRepository;

    /**
     * Finds a category by its ID.
     * @param id the ID of the category
     * @return an Optional containing the category if found, or an empty Optional if not found
     */
    @Override
    public Optional<DomainCategoryEntity> findById(UUID id) {
        try {
            return categoryRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error finding category by ID: {}", id, e);
            return Optional.empty();
        }
    }

    /**
     * Finds all categories.
     * @return a list of all categories
     */
    @Override
    public List<DomainCategoryEntity> findAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            logger.error("Error finding all categories", e);
            return List.of();
        }
    }
}