package ai.shreds.infrastructure;

import ai.shreds.domain.DomainPurchaseTransactionEntity;
import ai.shreds.domain.DomainPurchaseTransactionPort;
import ai.shreds.infrastructure.mapper.InfrastructureEntityMapper;
import ai.shreds.infrastructure.repository.PurchaseTransactionJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InfrastructurePurchaseTransactionRepositoryImpl implements DomainPurchaseTransactionPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructurePurchaseTransactionRepositoryImpl.class);

    private final PurchaseTransactionJpaRepository purchaseTransactionJpaRepository;
    private final InfrastructureEntityMapper entityMapper;

    @Autowired
    public InfrastructurePurchaseTransactionRepositoryImpl(PurchaseTransactionJpaRepository purchaseTransactionJpaRepository, InfrastructureEntityMapper entityMapper) {
        this.purchaseTransactionJpaRepository = purchaseTransactionJpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public void savePurchaseTransaction(DomainPurchaseTransactionEntity transaction) {
        try {
            PurchaseTransactionEntity jpaEntity = entityMapper.mapDomainToInfrastructure(transaction);
            purchaseTransactionJpaRepository.save(jpaEntity);
            logger.info("Purchase transaction saved successfully: {}", transaction.getPurchaseNumber());
        } catch (Exception e) {
            logger.error("Error saving purchase transaction: {}", transaction.getPurchaseNumber(), e);
            throw new RuntimeException("Failed to save purchase transaction", e);
        }
    }
}