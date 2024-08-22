package ai.shreds.infrastructure;

import ai.shreds.shared.SharedInventoryDomainEntity;
import ai.shreds.domain.DomainInventoryRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<SharedInventoryDomainEntity> findByProductId(UUID productId) {
        try {
            return Optional.ofNullable(entityManager.find(SharedInventoryDomainEntity.class, productId));
        } catch (Exception e) {
            logger.error("Error finding inventory by productId: {}", productId, e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(SharedInventoryDomainEntity inventory) {
        try {
            if (inventory != null) {
                entityManager.merge(inventory);
            } else {
                logger.warn("Attempted to save a null inventory object.");
            }
        } catch (Exception e) {
            logger.error("Error saving inventory: {}", inventory, e);
        }
    }

    @Override
    @Transactional
    public void deleteByProductId(UUID productId) {
        try {
            SharedInventoryDomainEntity inventory = findByProductId(productId).orElse(null);
            if (inventory != null) {
                if (entityManager.contains(inventory)) {
                    entityManager.remove(inventory);
                } else {
                    entityManager.remove(entityManager.merge(inventory));
                }
            }
        } catch (Exception e) {
            logger.error("Error deleting inventory by productId: {}", productId, e);
        }
    }
}