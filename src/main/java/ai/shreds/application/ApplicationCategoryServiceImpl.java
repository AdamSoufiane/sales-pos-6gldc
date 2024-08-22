package ai.shreds.application;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import ai.shreds.shared.ApplicationCategoryServiceException;
import ai.shreds.shared.ApplicationCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
@RequiredArgsConstructor
public class ApplicationCategoryServiceImpl implements ApplicationCategoryServicePort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryServiceImpl.class);

    private final DomainCategoryRepositoryPort categoryRepository;
    private final ApplicationCategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DomainCategoryEntity> getAllCategories() {
        try {
            logger.info("Fetching all categories");
            return categoryRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all categories", e);
            throw new ApplicationCategoryServiceException("Error fetching all categories", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DomainCategoryEntity getCategoryById(Long id) {
        try {
            logger.info("Fetching category by ID: {}", id);
            return categoryRepository.findById(id).orElseThrow(() -> new ApplicationCategoryServiceException("Category not found"));
        } catch (IllegalArgumentException e) {
            logger.error("Invalid ID format: {}", id, e);
            throw new ApplicationCategoryServiceException("Invalid ID format", e);
        } catch (Exception e) {
            logger.error("Error fetching category by ID: {}", id, e);
            throw new ApplicationCategoryServiceException("Error fetching category by ID", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DomainCategoryEntity> searchCategories(Map<String, String> query) {
        try {
            logger.info("Searching categories with query parameters: {}", query);
            validateQueryParameters(query);
            Specification<DomainCategoryEntity> spec = (Root<DomainCategoryEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> {
                Predicate p = cb.conjunction();
                if (query.containsKey("name")) {
                    p = cb.and(p, cb.like(root.get("name"), "%" + query.get("name") + "%"));
                }
                if (query.containsKey("categoryId")) {
                    p = cb.and(p, cb.equal(root.get("categoryId"), Long.parseLong(query.get("categoryId"))));
                }
                if (query.containsKey("createdAfter")) {
                    p = cb.and(p, cb.greaterThan(root.get("createdAt"), Timestamp.valueOf(query.get("createdAfter"))));
                }
                if (query.containsKey("updatedAfter")) {
                    p = cb.and(p, cb.greaterThan(root.get("updatedAt"), Timestamp.valueOf(query.get("updatedAfter"))));
                }
                return p;
            };
            return categoryRepository.findAll(spec);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid query parameter format: {}", query, e);
            throw new ApplicationCategoryServiceException("Invalid query parameter format", e);
        } catch (Exception e) {
            logger.error("Error searching categories with query parameters: {}", query, e);
            throw new ApplicationCategoryServiceException("Error searching categories", e);
        }
    }

    private void validateQueryParameters(Map<String, String> query) {
        if (query.containsKey("createdAfter")) {
            try {
                Timestamp.valueOf(query.get("createdAfter"));
            } catch (IllegalArgumentException e) {
                throw new ApplicationCategoryServiceException("Invalid createdAfter timestamp format", e);
            }
        }
        if (query.containsKey("updatedAfter")) {
            try {
                Timestamp.valueOf(query.get("updatedAfter"));
            } catch (IllegalArgumentException e) {
                throw new ApplicationCategoryServiceException("Invalid updatedAfter timestamp format", e);
            }
        }
    }
}