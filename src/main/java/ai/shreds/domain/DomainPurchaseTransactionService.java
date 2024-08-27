package ai.shreds.domain;

import ai.shreds.domain.DomainPurchaseTransactionEntity;
import ai.shreds.domain.DomainPurchaseTransactionPort;
import ai.shreds.domain.DomainPurchaseTransactionException;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainSupplierValidationPort;
import ai.shreds.domain.DomainProductValidationPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import java.util.List;

@Service
@AllArgsConstructor
public class DomainPurchaseTransactionService implements DomainPurchaseTransactionPort {

    private final DomainPurchaseTransactionPort purchaseTransactionRepository;
    private final DomainSupplierValidationPort supplierValidationService;
    private final DomainProductValidationPort productValidationService;

    @Override
    @Transactional
    public void savePurchaseTransaction(DomainPurchaseTransactionEntity transaction) {
        // Business rule: Validate supplier and product information before saving
        List<String> productIds = transaction.getProducts().stream().map(DomainProductEntity::getId).collect(Collectors.toList());
        if (supplierValidationService.validateSupplier(transaction.getSupplierId()) &&
            productValidationService.validateProducts(productIds)) {
            purchaseTransactionRepository.savePurchaseTransaction(transaction);
        } else {
            throw new DomainPurchaseTransactionException("Validation failed for supplier or products.");
        }
    }
}