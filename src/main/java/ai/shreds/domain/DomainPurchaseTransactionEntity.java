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
  
     // Add any additional validation or business logic here if necessary 
     public boolean isValid() { 
         return purchaseNumber != null && !purchaseNumber.isEmpty() && 
                purchaseDate != null && !purchaseDate.isAfter(LocalDateTime.now()) && 
                supplierId != null && !supplierId.isEmpty() && 
                products != null && !products.isEmpty(); 
     } 
 }