package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import ai.shreds.domain.DomainCategoryException;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.persistence.PersistenceException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(DomainCategoryEntity category) {
        try {
            if (category.getId() == null) {
                entityManager.persist(category);
            } else {
                entityManager.merge(category);
            }
        } catch (PersistenceException e) {
            throw new DomainCategoryException("Error saving category: a persistence error occurred", e);
        }
    }

    @Override
    public DomainCategoryEntity findById(UUID id) {
        try {
            return Optional.ofNullable(entityManager.find(DomainCategoryEntity.class, id))
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        } catch (PersistenceException e) {
            throw new DomainCategoryException("Error finding category: a persistence error occurred", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        try {
            DomainCategoryEntity category = findById(id);
            if (category != null) {
                entityManager.remove(category);
            }
        } catch (PersistenceException e) {
            throw new DomainCategoryException("Error deleting category: a persistence error occurred", e);
        }
    }
}