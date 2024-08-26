package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainPurchaseTransactionPort; 
 import ai.shreds.domain.DomainPurchaseTransactionEntity; 
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainSupplierValidationPort; 
 import ai.shreds.domain.DomainProductValidationPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.List; 
  
 @Service 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainPurchaseTransactionService implements DomainPurchaseTransactionPort { 
  
     private DomainPurchaseTransactionPort purchaseTransactionRepository; 
     private DomainSupplierValidationPort supplierValidationService; 
     private DomainProductValidationPort productValidationService; 
  
     @Override 
     @Transactional 
     public void savePurchaseTransaction(DomainPurchaseTransactionEntity transaction) { 
         // Business rule: Validate supplier and product information before saving 
         if (supplierValidationService.validateSupplier(transaction.getSupplierId()) && productValidationService.validateProducts(transaction.getProducts())) { 
             purchaseTransactionRepository.savePurchaseTransaction(transaction); 
         } else { 
             throw new DomainPurchaseTransactionException("Validation failed for supplier or products."); 
         } 
     } 
 } 
 