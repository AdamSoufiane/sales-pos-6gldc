package ai.shreds.infrastructure;

import ai.shreds.domain.DomainSupplierEntity;
import ai.shreds.domain.DomainSupplierRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Slf4j
public class InfrastructureSupplierRepositoryImpl implements DomainSupplierRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(DomainSupplierEntity supplier) {
        try {
            entityManager.persist(supplier);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public DomainSupplierEntity findById(Long id) {
        try {
            DomainSupplierEntity supplier = entityManager.find(DomainSupplierEntity.class, id);
            if (supplier == null) {
                throw new SupplierNotFoundException("Supplier not found with ID: " + id);
            }
            return supplier;
        } catch (Exception e) {
            handleException(e);
            throw new SupplierNotFoundException("Failed to find supplier with ID: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            DomainSupplierEntity supplier = findById(id);
            if (supplier != null) {
                entityManager.remove(supplier);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        try {
            return entityManager.find(DomainSupplierEntity.class, id) != null;
        } catch (Exception e) {
            handleException(e);
            return false;
        }
    }

    private void handleException(Exception e) {
        log.error("Database operation failed", e);
        throw new DatabaseOperationException("Database operation failed", e);
    }

    public static class SupplierNotFoundException extends RuntimeException {
        public SupplierNotFoundException(String message) {
            super(message);
        }
    }

    public static class DatabaseOperationException extends RuntimeException {
        public DatabaseOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}