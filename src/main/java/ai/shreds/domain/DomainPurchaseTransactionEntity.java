package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainPurchaseTransactionEntity {
    private String purchaseNumber;
    private LocalDateTime purchaseDate;
    private String supplierId;
    private List<DomainProductEntity> products;

    // Add validation or business logic
    public boolean isValid() {
        if (purchaseNumber == null || purchaseNumber.isEmpty()) {
            return false;
        }
        if (purchaseDate == null || purchaseDate.isAfter(LocalDateTime.now())) {
            return false;
        }
        if (supplierId == null || supplierId.isEmpty()) {
            return false;
        }
        if (products == null || products.isEmpty()) {
            return false;
        }
        for (DomainProductEntity product : products) {
            if (product == null || product.getProductId() == null || product.getProductId().isEmpty() || product.getPurchasePrice() <= 0 || product.getQuantity() <= 0) {
                return false;
            }
        }
        return true;
    }
}
