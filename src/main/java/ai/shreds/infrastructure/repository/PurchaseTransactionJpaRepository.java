package ai.shreds.infrastructure.repository;

import ai.shreds.infrastructure.entity.PurchaseTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTransactionJpaRepository extends JpaRepository<PurchaseTransactionEntity, Long> {
    // Additional custom query methods can be defined here if needed
}