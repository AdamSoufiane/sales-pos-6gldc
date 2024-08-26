package ai.shreds.domain;

import ai.shreds.infrastructure.InfrastructureInventoryRepositoryImpl;
import ai.shreds.infrastructure.InfrastructureException;
import ai.shreds.infrastructure.InfrastructureDatabaseInventoryModel;
import ai.shreds.infrastructure.InfrastructureInventoryRepositoryImplMapper;
import ai.shreds.domain.DomainInventoryEntity;
import ai.shreds.domain.DomainProductAddedEventException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DomainInventoryRepositoryService implements DomainInventoryRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(DomainInventoryRepositoryService.class);
    private final InfrastructureInventoryRepositoryImpl inventoryRepository;
    private final InfrastructureInventoryRepositoryImplMapper inventoryMapper;

    public DomainInventoryRepositoryService(InfrastructureInventoryRepositoryImpl inventoryRepository,
                                            InfrastructureInventoryRepositoryImplMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional
    @Override
    public void save(DomainInventoryEntity entity) {
        try {
            InfrastructureDatabaseInventoryModel dbModel = inventoryMapper.mapToDatabaseModel(entity);
            inventoryRepository.save(dbModel);
            log.info("Successfully saved inventory entity with productId: {}", entity.getProductId());
        } catch (InfrastructureException e) {
            log.error("Failed to save inventory entity with productId: {}", entity.getProductId(), e);
            throw new DomainProductAddedEventException("Failed to save inventory entity", e);
        }
    }

    @Cacheable(value = "inventoryEntityCache")
    @Override
    public DomainInventoryEntity findByProductId(String productId) {
        try {
            InfrastructureDatabaseInventoryModel dbModel = inventoryRepository.findByProductId(productId);
            log.info("Successfully found inventory entity with productId: {}", productId);
            return inventoryMapper.mapToDomainEntity(dbModel);
        } catch (InfrastructureException e) {
            log.error("Failed to find inventory entity by productId: {}", productId, e);
            throw new DomainProductAddedEventException("Failed to find inventory entity by productId", e);
        }
    }
}