package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainCategoryEntity> findAll() throws Exception {
        try {
            String jpql = "SELECT c FROM DomainCategoryEntity c";
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all categories", e);
        }
    }

    @Override
    public DomainCategoryEntity findById(Long id) throws Exception {
        try {
            return entityManager.find(DomainCategoryEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve category by ID", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findByNameContaining(String name) throws Exception {
        try {
            String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.name LIKE :name";
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class);
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to search categories by name", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findByCategoryId(Long categoryId) throws Exception {
        try {
            String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.categoryId.id = :categoryId";
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class);
            query.setParameter("categoryId", categoryId);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to search categories by parent category ID", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findByCreatedAtAfter(Timestamp createdAfter) throws Exception {
        try {
            String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.createdAt > :createdAfter";
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class);
            query.setParameter("createdAfter", createdAfter);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to search categories by creation date", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findByUpdatedAtAfter(Timestamp updatedAfter) throws Exception {
        try {
            String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.updatedAt > :updatedAfter";
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class);
            query.setParameter("updatedAfter", updatedAfter);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to search categories by update date", e);
        }
    }

    public Page<DomainCategoryEntity> findAll(Pageable pageable) throws Exception {
        try {
            String jpql = "SELECT c FROM DomainCategoryEntity c";
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class);
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            return new PageImpl<>(query.getResultList(), pageable, count());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve paginated categories", e);
        }
    }

    private long count() {
        try {
            String countJpql = "SELECT COUNT(c) FROM DomainCategoryEntity c";
            TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);
            return countQuery.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Failed to count all categories", e);
        }
    }
}