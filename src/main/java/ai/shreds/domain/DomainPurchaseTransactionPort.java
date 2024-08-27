package ai.shreds.domain;

import ai.shreds.domain.DomainPurchaseTransactionEntity;

/**
 * Port interface for handling purchase transaction persistence.
 */
public interface DomainPurchaseTransactionPort {

    /**
     * Saves the given purchase transaction entity.
     *
     * @param transaction the purchase transaction entity to save
     */
    void savePurchaseTransaction(DomainPurchaseTransactionEntity transaction);
}