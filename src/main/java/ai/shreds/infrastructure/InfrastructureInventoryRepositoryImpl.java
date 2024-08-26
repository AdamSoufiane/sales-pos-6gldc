package ai.shreds.infrastructure;

import ai.shreds.domain.DomainInventoryEntity;
import ai.shreds.domain.DomainInventoryRepositoryPort;
import ai.shreds.infrastructure.exception.InfrastructureException;
import ai.shreds.infrastructure.mapper.InfrastructureInventoryRepositoryImplMapper;
import ai.shreds.infrastructure.model.InfrastructureDatabaseInventoryModel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImpl.class);
    private final MongoTemplate mongoTemplate;
    private final InfrastructureInventoryRepositoryImplMapper mapper;

    @Override
    public void save(@NonNull DomainInventoryEntity entity) {
        try {
            InfrastructureDatabaseInventoryModel model = mapper.mapToDatabaseModel(entity);
            mongoTemplate.save(model);
            logger.info("Saved inventory entity with productId: {}", entity.getProductId());
        } catch (Exception e) {
            logger.error("Failed to save inventory entity with productId: {}", entity.getProductId(), e);
            throw new InfrastructureException("Failed to save inventory entity with productId: " + entity.getProductId(), e);
        }
    }

    @Override
    public DomainInventoryEntity findByProductId(String productId) {
        try {
            Query query = new Query(Criteria.where("productId").is(productId));
            InfrastructureDatabaseInventoryModel model = mongoTemplate.findOne(query, InfrastructureDatabaseInventoryModel.class);
            logger.info("Found inventory entity with productId: {}", productId);
            return model != null ? mapper.mapToDomainEntity(model) : null;
        } catch (Exception e) {
            logger.error("Failed to find inventory entity with productId: {}", productId, e);
            throw new InfrastructureException("Failed to find inventory entity with productId: " + productId, e);
        }
    }
}